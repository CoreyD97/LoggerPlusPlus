/* Generated By:JJTree: Do not edit this line. ASTAlias.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.nccgroup.loggerplusplus.filter.parser;

public
class ASTAlias extends SimpleNode {

  public String identifier;
  public ASTExpression filter;

  public ASTAlias(int id) {
    super(id);
  }

  public ASTAlias(FilterParser p, int id) {
    super(p, id);
  }

  @Override
  public String getFilterString() {
    return "#" + identifier;
  }

  @Override
  public String toString() {
    return String.format("ASTAlias[id=%s]", identifier);
  }

  /** Accept the visitor. **/
  public Object jjtAccept(FilterParserVisitor visitor, VisitorData data) {

    return
    visitor.visit(this, data);
  }
}