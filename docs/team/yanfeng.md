# Zou Yanfeng’s Portfolio for CS2113 TP

## Code Contributions

### Delete Module Feature
What it does: Delete an entire module based on module code. All the tasks belonging to the module will also be deleted.
Justification: There are times when a module is no longer needed. For example, when a module is dropped. Hence the module will no longer be needed, neither nor its associated tasks such as deadlines, lessons, events.
Highlights: While the Delete module function is a function that primarily deals with ModuleManager, the duality nature of ModuleManager and ScheduleManager means that Delete Module also deletes all tasks that have its Module Code from ScheduleManager.

### Delete Module Task feature
What it does: There are two sub-functions of this feature, depending on user input
Delete a module’s task with the date and description: Users can search for a particular task based on its own description on a certain date, and delete it. E.g. A cancelled lecture on a particular date.
Delete Module’s task based on dates: Users can choose to delete everything from the module on that date. E.g. A public holiday.
Justification: Given that there can be many different types of tasks each carrying different attributes, it is expected that the user would want to search for tasks via different properties that is most convenient for them in order to delete it.
Highlights: Many tasks have different but similar attributes. For examples, events and lessons have timings, just like deadlines, but the deadline timing works differently from that of events and lessons. Hence more time have to be spent fixing these issues so we could search by date.


### Delete Task Feature
What it does: Similar to Module task delete, Delete Task deletes tasks but from the ScheduleManager instead of ModuleManager.
Justification: Not all task belong to modules.  Users might also want to delete tasks from different modules. E.g. cancelling all rest days....
Highlights: Required deletion of tasks from ModuleManger as well as Schedulemanager

### Improving Parser for edit and delete commands.
What it does: Parser handles the text processing from the user input. Improvement was made to Parser to allow it to recognise different user input command 
Justification: 
Highlights: Text processing was tedious as there was many different outcome commands, yet the inputs always start with “edit” and “delete” only.

## Documentation

### Adding to UG:
Updated UG with the documentation for edit, delete features.
Add manual testing test cases and expected output for new users.

### Adding to DG
Added implementation details and class diagram for the delete feature #257

### Project management
Added and managed issues for milestones v1.0, v2.0, v2.1.

### Enhancements to existing features
Debugged issues for edit and delete features, as well as other components in the project
Manual testing, reported bugs and suggestions.

### Community
Reported bugs and suggestions for other teams in the class.


https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=teachyourselfcoding&tabRepo=AY2021S1-CS2113-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code