Logger++ - Advanced Logging for Burp Suite
=======================
![Build](https://github.com/nccgroup/LoggerPlusPlus/workflows/Java%20CI%20with%20Gradle/badge.svg)

Sometimes it is necessary to log all the requests and responses of a specific tool in Burp Suite. Logger++ can log activities of all the tools in Burp Suite to show them in a sortable table. It also has an ability to save this data in CSV format.

Developed by Corey Arthur [@CoreyD97](https://twitter.com/CoreyD97)

Original by Soroush Dalili [@irsdl](https://twitter.com/irsdl)

Released as open source by NCC Group Plc - https://www.nccgroup.trust/

Released under AGPL see LICENSE for more information

<br />

Screenshots
----------------------

<b>Log Filters</b>

![Log Filters](images/filters.png)

<b>Row Highlights</b>

![Row Highlights](images/colorfilters.png)

<b>Grep Search</b>

![Grep Panel](images/grep.png)

<br />


### Using the application

You can use this extension without using the BApp store. In order to install the latest version of this extension from the GitHub repository, follow these steps:

Step 1. (Downloading) Download the [latest release jar](https://github.com/nccgroup/LoggerPlusPlus/releases/latest).

Step 2. (Adding to Burp) In Burp Suite, click on the "Extender" tab, then in the "Extensions" tab click on the "Add" button and select the downloaded "burplogger++.jar" file.

Step 3. (Testing) Now you should be able to see the "Logger++" tab in Burp Suite. If it cannot log anything, check your Burp Suite extension settings. If the save buttons are disabled, make sure that the requested libraries have been loaded successfully; Unload and then reload the extension and try again. If you have found an issue, please report it in the GitHub project.

Step 4. (Configuring) You can configure this extension by using its "option" tab and by right click on the columns' headers.

Step 5. (Using!) Now you can use this extension!

<b>Requirements:</b>
- Latest version of Burp Suite
- Java version 7 or above

<b>Features:</b>

- Works with the latest version of Burp Suite (tested on 1.7.27)
- Logs all the tools that are sending requests and receiving responses
- Ability to log from a specific tool
- Ability to save the results in CSV format
- Ability to show results of custom regular expressions in request/response
- User can customise the column headers
- Advanced Filters can be created to display only requests matching a specific string or regex pattern.
- Row highlighting can be added using advanced filters to make interesting requests more visible.
- Grep through logs.
- Live requests and responses.
- Multiple view options.
- Pop out view panel.
- Multithreaded.

<b>Current Limitations:</b>

- Cannot log the requests' actual time unless originating from proxy tool.
- Cannot calculate the actual delay between a request and its response unless originating from proxy tool.

<b>Reporting bugs:</b>

If you have found an issue, please report it in the GitHub project.

<b>Latest version:</b>

Please review the ["CHANGELOG"](CHANGELOG)
