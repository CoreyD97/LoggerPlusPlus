package com.nccgroup.loggerplusplus.filter.parser;

import com.nccgroup.loggerplusplus.filter.savedfilter.SavedFilter;
import com.nccgroup.loggerplusplus.filterlibrary.FilterLibraryController;
import com.nccgroup.loggerplusplus.logentry.FieldGroup;
import com.nccgroup.loggerplusplus.logentry.LogEntryField;
import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.stream.Collectors;

@Log4j2
public class AliasCheckVisitor implements FilterParserVisitor{

  private FilterLibraryController filterLibraryController;

  public AliasCheckVisitor(FilterLibraryController filterLibraryController){
    this.filterLibraryController = filterLibraryController;
  }

  public VisitorData defaultVisit(SimpleNode node, VisitorData data){
    node.childrenAccept(this, data);
    return data;
  }

  @Override
  public VisitorData visit(SimpleNode node, VisitorData data){
    return defaultVisit(node, data);
  }

  public VisitorData visit(String alias, SimpleNode node){
    log.debug("Starting sanity check on expression (%s) alias (%s) ".formatted(node.toString(), alias));
    VisitorData visitorData = new VisitorData();
    visitorData.setData("dependencies", new HashSet<String>());
    visitorData.setData("contexts", new HashSet<FieldGroup>());
    Stack<String> visitStack = new Stack<String>();
    visitorData.setData("aliasVisitList", visitStack);
    if (alias != null) {
      visitStack.push(alias.toUpperCase());
    }
    return visit(node, visitorData);
  }

  @Override
  public VisitorData visit(ASTExpression node, VisitorData data){
    return defaultVisit(node, data);
  }

  @Override
  public VisitorData visit(ASTComparison node, VisitorData visitorData){
    HashSet<FieldGroup> contexts = (HashSet<FieldGroup>) visitorData.getData().get("contexts");
    if(node.left instanceof LogEntryField) contexts.add(((LogEntryField) node.left).getFieldGroup());
    if(node.right instanceof LogEntryField) contexts.add(((LogEntryField) node.right).getFieldGroup());
    defaultVisit(node, visitorData);
    return visitorData;
  }

  @Override
  public VisitorData visit(ASTAlias node, VisitorData data) {
    //Add this alias to our dependencies
    Stack<String> aliasVisitList = (Stack<String>) data.getData().get("aliasVisitList");
    try {
      log.debug("Visiting " + node.identifier);
      if (aliasVisitList.contains(node.identifier.toUpperCase())) {
        //We're recursing, don't continue!
        aliasVisitList.push(node.identifier.toUpperCase());
        String visitOrder = aliasVisitList.stream().collect(Collectors.joining("->"));
        data.addError("Recursion detected in filter. Alias trace: " + visitOrder);
        return data;
      }

      aliasVisitList.push(node.identifier.toUpperCase());
      log.debug("Current Visit Queue " + aliasVisitList.toString());


      ((HashSet<String>) data.getData().get("dependencies")).add(node.identifier.toUpperCase());

      //Now sanity check on the aliased filter with our existing data
      Optional<SavedFilter> aliasedFilter = filterLibraryController.getFilterSnippets().stream().filter(savedFilter -> savedFilter.getName().equalsIgnoreCase(node.identifier)).findFirst();
      if (aliasedFilter.isPresent()) {
        visit(aliasedFilter.get().getFilterExpression().getAst(), data);
      } else {
        data.addError("Could not find a filter in the library for alias: " + node.identifier);
      }


      return data;
    }finally {
      log.debug("Leaving " + node.identifier);
      aliasVisitList.pop();
      log.debug("Current Visit Queue " + aliasVisitList.toString());
    }
  }
}