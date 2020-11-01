User Guide

## Introduction

**DueQuest** is an application which helps NUS students manage their schedule in the semester
as well as tasks related to their modules that they are taking.

## Quick Start

1. Ensure that you have Java 11 or above installed on the machine.
1. Down the latest version of `DueQuest` from [here](http://link.to/duke), and put the jar file into a directory.
1. Type `java -jar DueQuest.jar` to start the app 

---

## Storage 

The storage directory is specified when launching `java -jar DueQuest.jar SPECIFIED_DIR`. By default, the directory is `data`. In the storage directory, each module will have a txt file that contains related information (e.g. information and tasks), and `additional.txt` is for commands such as delete certain tasks on certain dates. 

The information will be imported and exported automatically by the app. 

## Add a Module: `module`

Format: `module c/MODULECODE  [t/TITLE] [a/AU_NUM] [s/TEACHING_STAFF]`

+ `MODULECODE` must be given, and it should be unique. 
  + There are 3 types of valid module codes. 
    * 6 characters long. The first 2 characters are alphabets. The last 4 characters are digits. Example: CS2113
    * 7 characters long. The first 3 characters are alphabets. The last 4 characters are digits. Example: DSA4211
    * 7 characters long. The first 2 characters are alphabets. The next 4 characters are digits. The last character is an alphabet. The  Example: CS2113T
+ `AU_NUM` must be non-negative integer. 

Example Usage: 

`module c/ST2113 a/4 s/Dr.Lim s/ChengChen`
module c/CS2113 a/4 s/Dr.Lim s/ChengChen
```
===================
module c/ST2113 a/4 s/Dr.Lim s/ChengChen
Successfully added to Module Manager! Have fun suffering from 
Course: ST2113
Title: null
AU: 4
Teaching Staffs: [Dr.Lim, ChengChen]
Here are your modules you are currently taking! 
[ST2113]
===================
```

```
module c/ST2113
===================
The module with the same code already exists.
===================
```

## Task

A Task can be a Lesson, Event or Deadline.

+ Lessons are classes that are held **on every week** in your timetable.
+ Events are one-off, they only take place on a single day.
+ Deadline are assignments where there is a due date.

## Adding a lesson: `lesson`

Adds a new Lesson into both your Schedule Manager and Module Manager.
Lesson will only be added into weeks when there are lessons conducted according to the NUS curriculum.
This means that the lesson will not be added  into dates during Reading Weeks, Vacation, and Examination weeks.
If there is a clash in timings detected (the time of the lesson to be added clashes with another lesson or event), the
lesson will not be added.

`lesson TITILE COURSECODE /on DAYOFWEEK  STARTTIME ENDTIME`

* The `DAYOFWEEK` is the Day Of Week when the lesson is conducted on, starting from Monday (e.g. 1). Type is an integer. If the lesson is held on Thursday, type in 4. If the lesson is held on Wednesday, type in 3.
* The `START_TIME` and `END_TIME` are both in `HH:MM` format. You are only allowed to type in timings such that `MM` is `00`. For example, `18:00` is allowed but not `18:01`.

Examples of Usage:

* If you want to add a CS2113 online lecture lesson which starts at 4pm and ends at 6pm, held on every Friday (every 7 days): `lesson online lecture CS2113 /on 5 16:00 18:00`.
* If you want to add a CS1234 online tutorial lesson which starts at 8am and ends at 10am, held on every Thursday (every 7 days): `lesson online tutorial CS1234 /on 4 08:00 10:00`

Example Usage:

```
===================
lesson online lecture CS2113 /on 5 16:00 18:00
Got it, added lesson to the schedule manager!
===================
===================
lesson online tutorial CS1234 /on 4 08:00 10:00
Got it, added lesson to the schedule manager!
===================
```

## Adding an event: `event`

Adds an Event asscociated with a module into the Schedule Manager and Module Manager
If there is a clash in timings detected (the time of the event to be added clashes with another lesson or event), the
event will not be added.

Format: `event MODULE_CODE DESCRIPTION /at DATE_OF_EVENT START_TIME END_TIME LOCATION_OF_EVENT`

* `TIME` is in `HH:MM` format. (For now, since the display function only displays timings for every hour, the time of event is such that `MM` needs to be `00`)
* `DATE_OF_EVENT` is in `yyyy-mm-dd` format.
* For now, `LOCATION_OF_EVENT` is one word only.

Example Usage:

* If you want to add an event called final exam for module CS2113 at 3rd May 2021, from 2pm to 4pm at LT14, input
 `event CS2113 final exam /at 2021-05-03 14:00 16:00 LT14`

```
===================
event CS2113 final exam /at 2021-05-03 14:00 16:00 LT14
Event added to both Schedule manager and Module manager
===================
```

## Adding a deadline: `deadline`

Adds a deadline of an assignment into the Schedule Manager and Module Manager.

Format: `deadline COURSECODE DESCRIPTION /by DATE `

Examples input:
* If you want to add a deadline for CS2113 for TP version 1 at 4th April 2021, input `deadline CS2113 TP version 1 /by 2021-04-04`

```
===================
deadline CS2113 TP version 1 /by 2021-04-04
Got it, added deadline to Schedule Manager and Module Manager
===================
```

## Display today's Schedule: `display` 

Display Today's Lesson and Task( Deadline and Event)

Task that are in the same time slot are Separated with "|"

Format: `display`

Example input:

`display`

Example output:

```
Today's Schedule:
08:00
09:00
10:00 quiz - CS2113 |
11:00 quiz - CS2113 | meeting - CS2113 |
12:00 lecture - CS2113 | meeting - CS2113 |
13:00 lecture - CS2113 | meeting - CS2113 |
14:00 lecture - CS2113 |
15:00 lecture - CS2113 |
16:00
17:00
18:00
19:00
20:00
21:00
22:00
23:00

Today's task:
You don't have any tasks!


```

## Display all the task in a module: `display  MODULECODE`

 Display task in a specific module

 Format: `display  MODULECODE`
 Example of Usage

 * `display CS2113`

```
display CS2113

Course: CS2113
Title: null
AU: 0
Teaching Staffs: []
The list of task in CS2113:
[E] CS2113 quiz (10:00 2020-10-21 at LT15)
[L] lecture - CS2113 WEDNESDAY 12:00 16:00
[E] CS2113 meeting (11:00 2020-10-21 at LT16)
[D] TP version 1 - CS2113  (by: 2020-10-21)

===================
```

## Display all the task in a module on a date: `display  MODULECODE /date DATE`

  Display task in a specific module on a specific Date

  Format: `display  MODULECODE /date DATE`
  * The `DATE` must be in `YYYY/MM/DD`

  Example of Usage

  * `display CS2113 /date 2020/10/15`

```
display CS2113

Course: CS2113
Title: null
AU: 0
Teaching Staffs: []
The list of task in CS2113:
[E] CS2113 quiz (10:00 2020-10-21 at LT15)
[L] lecture - CS2113 WEDNESDAY 12:00 16:00
[E] CS2113 meeting (11:00 2020-10-21 at LT16)
[D] TP version 1 - CS2113  (by: 2020-10-21)

===================
```

## Display all the task on a date: `display /date DATE`

Display the schedule on a specific Date.
Also shows the all the upcoming deadlines one week from the specified date. 
Format: `display /date DATE`

* The `DATE` must be in `YYYY/MM/DD`
  

Example of Usage
* `display /date 2020/10/15`

```
display /date 2020/10/21
Today's Schedule:
08:00
09:00
10:00 quiz - CS2113 |
11:00 quiz - CS2113 | meeting - CS2113 |
12:00 lecture - CS2113 | meeting - CS2113 |
13:00 lecture - CS2113 | meeting - CS2113 |
14:00 lecture - CS2113 |
15:00 lecture - CS2113 |
16:00
17:00
18:00
19:00
20:00
21:00
22:00
23:00

Upcoming deadlines:
[D] TP version 1 - CS2113  (by: 2020-10-21)

```

## Display all the task on a range of date: `display /date STARTDATE-ENDDATE`

Display task on a specific range of days
Format: `display /date STARTDATE-ENDDATE`

* The `STARTDATE/ENDDATE` must be in `YYYY/MM/DD`
* Do note the Dash `-` to indicate the end date
  

Example of Usage
* `display /date 2020/10/13-2020/10/16`

```
===================
display /date 2020/10/13-2020/10/16
List of task from 2020-10-13 to 2020-10-16
Oct 14 schedule :
[L]Lecture ST2132 14:00 16:00

Oct 15 schedule :
[L]tutorial CS2113 13:00 14:00
[D]CS2113 Tp version 1 (by: 2020-10-15)

===================
```

## Edit a task: `edit TASKNAME /date DATE /	/ATTRIBUTES /NEW_VALUE`

Edit the parameter(description, date, frequency, modulecode, time, tasktype) of a certain task
Format: `edit TASKNAME /date DATE /ATTRIBUTES /NEW_VALUE`

Example of Usage: 

```
===================
deadline CS2113 tp /by 2020-10-16
Got it, added deadline to Schedule Manager and Module Manager
===================
===================
Please type the command!
===================
edit tp /date 2020-10-16 /date /2020-10-15
2020-10-16
===================
Please type the command!
===================
display CS2113

Course: CS2113
Title: null
AU: 0
Teaching Staffs: []
The list of task in CS2113:
[D]CS2113 tp (by: 2020-10-15)
===================
```

## Delete tasks of a description:  `delete DESCRIPTION` 

Delete every task that fits the description regardless of date
Format: `delete DESCRIPTION` 

Example of Usage:

```
===================
deadline CS2113 tp /by 2020-10-16
Got it, added deadline to Schedule Manager and Module Manager
===================
===================
Please type the command!
===================
delete tp
===================
Please type the command!
===================
display CS2113

Course: CS2113
Title: null
AU: 0
Teaching Staffs: []
The list of task in CS2113:
You don't have any tasks!

===================
===================
Please type the command!
===================

```

## Delete all tasks of a description based on date: `delete DESCRIPTION /date DATE`

Delete every task that fits the description on that particular date
Format: `delete DESCRIPTION /date DATE`

* The DATE must be in YYYY/MM/DD

Example of Usage

```
===================
===================
Please type the command!
===================
deadline CS2113 tp /by 2020-10-15
Got it, added deadline to Schedule Manager and Module Manager
===================
===================
Please type the command!
===================
deadline CS2113 tp /by 2020-10-16
Got it, added deadline to Schedule Manager and Module Manager
===================
===================
Please type the command!
===================
deadline CS2113 tp /by 2020-10-15
Got it, added deadline to Schedule Manager and Module Manager
===================
===================
Please type the command!
===================
delete tp /date 2020/10/16
===================
Please type the command!
===================
display CS2113

Course: CS2113
Title: null
AU: 0
Teaching Staffs: []
The list of task in CS2113:
[D]CS2113 tp (by: 2020-10-15)
[D]CS2113 tp (by: 2020-10-15)

===================
===================
Please type the command!
===================
display /date 2020/10/16
Here is your schedule on 2020-10-16!! :)
08:00
09:00
10:00
11:00
12:00
13:00
14:00
15:00
16:00
17:00
18:00
19:00
20:00
21:00
22:00
23:00

Deadlines on 2020-10-16:
You don't have any tasks!
```

## Exiting the app: `bye`
Exits the app and saves the tasks in the txt files.
Format: `bye`

Example of Usage:
Input: `bye`
Output: `Aye captain. This is DueQuest Signing out!`


# Command Summary

+ Add Module: `module c/MODULECODE  [t/TITLE] [a/AU_NUM] [s/TEACHING_STAFF]`
+ Add Lesson: `lesson TITILE COURSECODE /on DAYOFWEEK 7 STARTTIME ENDTIME`
+ Add Event: `event MODULE_CODE DESCRIPTION /at DATE_OF_EVENT TIME LOCATION_OF_EVENT`
+ Add Deadline: `deadline COURSECODE DESCRIPTION /by DATE`
+ Display today's Schedule: `display` 
+ Display all the task in a module: `display  MODULECODE`
+ Display all the task in a module on a date: `display  MODULECODE /date DATE`
+ Display all the task on a date: `display /date DATE`
+ Display all the task on a range of date: `display /date STARTDATE-ENDDATE`
+ Edit a task: `edit TASKNAME /date DATE /	/ATTRIBUTES /NEW_VALUE`
+ Delete tasks of a description:  `delete DESCRIPTION` 
+ Delete all tasks of a description based on date: `delete DESCRIPTION /date DATE`

