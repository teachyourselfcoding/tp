# User Guide

## Introduction

DueQuest is an application which helps NUS students manage their schedule in the semester
as well as tasks related to their modules that they are taking.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `DueQuest` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Adding a todo: `todo` 
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### Adding a module

Format: `add_module c/MODULECODE  t/TITLE a/AU_NUM s/TEACHING_STAFF`

Input: `add_module c/ST2113 a/4 s/Lim`

Output:

```
===================
Successfully added to Module Manager! Have fun suffering from Course: ST2113
Title: null
Teaching Staffs: [Lim]
Here are your modules you are currently taking! 
[ST2113]
```



Adds a module into the module manager if it hasn't exist.

Format: `module /module_code MODULE_CODE`

* The `MODULE_CODE` has to be in a valid format.
* There are 3 types of valid module codes. 
  * 6 characters long. The first 2 characters are alphabets. The last 4 characters are digits. Example: CS2113
  * 7 characters long. The first 3 characters are alphabets. The last 4 characters are digits. Example: DSA4211
  * 7 characters long. The first 2 characters are alphabets. The next 4 characters are digits. The last character is an alphabet. The  Example: CS2113T

Example of Usage

* `module MA4270`
* `module CS2113T`

## Adding a Task

Adds a new task into the schedule manager according to which day the task stated.
A Task can be a Lesson, Event or Deadline.

+ Lessons are classes that are held on every week in your timetable.
+ Events are one-off, they only take place on a single day.
+ Deadline are assignments where there is a due date.

### Adding a lesson: 'lesson'

Adds a new Lesson into both your Schedule Manager and Module Manager

1

* The `DAY` is the Day Of Week when the lesson is conducted on. Type in an integer. If the lesson is held on Thursday, type in 4. If the lesson is held on Wednesday, type in 3.
* The `START_TIME` and `END_TIME` are both in `HH:MM` format. You are only allowed to type in timings such that `MM` is `00`. For example, `18:00` is allowed but not `18:01`.

Examples of Usage:

* If you want to add a CS2113 online lecture lesson which starts at 4pm and ends at 6pm, held on every Friday (every 7 days): `lesson online lecture CS2113 /on 5 7 16:00 18:00`.
* If you want to add a CS1234 online tutorial lesson which starts at 12pm and ends at 2pm, held on every Thursday (every 7 days): `lesson online tutorial CS1234 /on 4 7 08:00 10:00`

Example I/O:

```
===================
lesson lecture online CS2113 /on 5 7 16:00 18:00
Got it, added lesson to the schedule manager!
===================
===================
Please type the command!
===================
display CS2113

Course: CS2113
Title: null
AU: 0
Teaching Staffs: []
The list of task in CS2113:
[L]lecture online CS2113 16:00 18:00

===================
```



### Adding an event: 'event'
Adds an Event into the Schedule Manager. If the Event is associated to a Module, it will be added to the Module Manager as well.

Format: `event m/MODULE_CODE d/DESCRIPTION /at d/DATE_OF_EVENT t/TIME l/LOCATION_OF_EVENT`

* `TIME` is in `HH:MM` format. (For now, since the display function only displays timings for every hour, the time of event is such that `MM` needs to be `00`)
* `DATE_OF_EVENT` is in `yyyy-mm-dd` format.
* For now, `LOCATION_OF_EVENT` is one word only.

Examples of Usage:

* `event CS2113 final exam /at 2021-05-03 14:00 16:00 LT14`.

### Adding a deadline: 'deadline'
Adds a deadline of an assignment into the Schedule Manager and Module Manager.

Format: `deadline d/description `

Examples of Usage:

* `deadline CS2113 TP version 1 /by 2021-04-04`

##Display task on the Screen
You can display the task and limit the scope using moduleCode and Date/ Date Range.

###Display today's Schedule
Display Today's Lesson and Task( Deadline and Event)

Format: `display`
Example of Usage

* `display`

 ###Display all the task in a module
 Display task in a specific module

 Format: `display  MODULECODE`
 Example of Usage

 * `display CS2113`

 ###Display all the task in a module on a date
  Display task in a specific module on a specific Date

  Format: `display  MODULECODE /date DATE`
  * The `DATE` must be in `YYYY/MM/DD`

  Example of Usage

  * `display CS2113 /date 2020/10/12`
  * `display ST2334 /date 2021/01/12`

###Display all the task on a date
Display task on a specific Date
    
Format: `display /date DATE`
* The `DATE` must be in `YYYY/MM/DD`
  

Example of Usage
* `display /date 2020/10/12`
* `display /date 2021/01/12`

###Display all the task on a range of date
Display task on a specific range of days
    
Format: `display /date STARTDATE-ENDDATE`
* The `STARTDATE/ENDDATE` must be in `YYYY/MM/DD`
* Do note the Dash `-` to indicate the end date
  

Example of Usage
* `display /date 2020/10/12-2020/12/12`
* `display /date 2020/10/12-2021/01/12`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}
