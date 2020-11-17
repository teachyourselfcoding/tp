# Sun XiuQi’s Portfolio for CS2113 TP

## Code Contributions:

### Edit Module Feature 
What it does: Allow the user to edit features of the module itself. Users can edit the module code, change the modular credits, as well as edit the staff involved with this module.
Justification: This is integral to the program as we have to allow changes to the module details once tasks are created so users are not forced to delete the module and create a new one if wrong information was keyed in.
Highlights: Every task can choose to carry information about the module such as module code, modular credits, staff information etc. Hence it became harder than simpler changing properties of the module itself, instead, each task has to be tracked down and checked.

###  Edit Module Task feature 
What it does: This allow the user to edit several properties of the task that exist within the module. Properties include task description, time information, date information, task’s frequency, task type, etc. Since many tasks are repetitive, the program allows the user to choose the date where he can edit all tasks pertaining to a module, or he can edit all instances of a task from the module.
Justification: From our user story we identified there are different situations that a student would want to edit attributes of tasks. For example, if the lecture that falls on every Friday changes the timing, the user would want to edit the timing of all instances of that task. If a one time talk is postponed, the user will want to edit the task that falls on one particular day.
Highlights: Since the attributes were written by different coders, editing these attributes was tedious as each method was likely different. Long hours were also spent to understand the logic of their code and to edit their properties.

###  Edit Task feature. 
What it does: Similar to Edit module task, Users can edit several properties of various tasks. However, these tasks may or may not belong to any modules. Hence Edit Module Task caters to ModuleManager more, but Edit task caters to ScheduleManager. Users can edit all tasks that match a description or limit them to a single date.
Justification: Since ModuleManager and ScheduleManager works as two interlinked entities, Edit task feature is required to pertain to tasks that do not belong to any modules.
Highlights: Was hard to implement as we had to sync it to Edit Module Task. If we delete a module’s tasks, we have to delete it in ScheduleManager. If we delete it from ScheduleManager then we have to check if it exists inside ModuleManager.

###  Improved UI Feature
What it does: UI class is the interface that interacts with the users, such as sending them error messages or acknowledgements. Error messages and acknowledgements throughout the programs were collected and consolidated such that they were all sent through the UI.
Justification: Tidying up the UI improves readability and uniformity through consolidating all the print messages from other classes into the UI class. 
Highlights: Tedious as there was a lot of different messages that were all different. However, this process also eliminated a lot of test print messages that were used for printing that were not caught during product testing.

## Documentation 
User Guide:
Added documentation for the features edit, delete (#)

###  Developer Guide:
Added implementation details and sequence diagram for the edit feature. (#133)


###  Project management
Added and Managed issues for milestones v1.0, v2.0, v2.1.

https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=teachyourselfcoding&tabRepo=AY2021S1-CS2113-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code
