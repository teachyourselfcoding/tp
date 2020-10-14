# User Guide

## Introduction

**DueQuest** is an application which helps NUS students manage their schedule in the semester
as well as tasks related to their modules that they are taking.

## Quick Start

1. Ensure that you have Java 11 or above installed on the machine.
1. Down the latest version of `DueQuest` from [here](http://link.to/duke), and put the jar file into a directory.
1. Type `java -jar DueQuest.jar` to start the app 

---



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

Adds a new Lesson into both your Schedule Manager and Module Manager

`lesson TITILE COURSECODE /on DAYOFWEEK 7 STARTTIME ENDTIME`

* The `DAYOFWEEK` is the Day Of Week when the lesson is conducted on, starting from Monday (e.g. 1). Type is an integer. If the lesson is held on Thursday, type in 4. If the lesson is held on Wednesday, type in 3.
* The `START_TIME` and `END_TIME` are both in `HH:MM` format. You are only allowed to type in timings such that `MM` is `00`. For example, `18:00` is allowed but not `18:01`.

Examples of Usage:

* If you want to add a CS2113 online lecture lesson which starts at 4pm and ends at 6pm, held on every Friday (every 7 days): `lesson online lecture CS2113 /on 5 7 16:00 18:00`.
* If you want to add a CS1234 online tutorial lesson which starts at 12pm and ends at 2pm, held on every Thursday (every 7 days): `lesson online tutorial CS1234 /on 4 7 08:00 10:00`

Example Usage:

```
===================
lesson online lecture CS2113 /on 5 7 16:00 18:00
Got it, added lesson to the schedule manager!
===================
===================
lesson online tutorial CS1234 /on 4 7 08:00 10:00
Got it, added lesson to the schedule manager!
===================
```

## Adding an event: `event`

Adds an Event into the Schedule Manager. If the Event is associated to a Module, it will be added to the Module Manager as well.

Format: `event MODULE_CODE DESCRIPTION /at DATE_OF_EVENT TIME LOCATION_OF_EVENT`

* `TIME` is in `HH:MM` format. (For now, since the display function only displays timings for every hour, the time of event is such that `MM` needs to be `00`)
* `DATE_OF_EVENT` is in `yyyy-mm-dd` format.
* For now, `LOCATION_OF_EVENT` is one word only.

Example Usage:

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

`deadline CS2113 TP version 1 /by 2021-04-04`

```
===================
deadline CS2113 TP version 1 /by 2021-04-04
Got it, added deadline to Schedule Manager and Module Manager
===================
```

## Display today's Schedule: `display` 

Display Today's Lesson and Task( Deadline and Event)

Format: `display`

Example input:

`display`

Example output:

```
Today's Schedule:
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

 Today's task:
You don't have any tasks!


```

## Display all the task in a module: `display  MODULECODE`

 Display task in a specific module

 Format: `display  MODULECODE`
 Example of Usage

 * `display CS2113`

```
===================
display CS2113 

Course: CS2113
Title: null
AU: 0
Teaching Staffs: []
The list of task in CS2113:
[L]tutorial CS2113 13:00 14:00
[D]CS2113 Tp version 1 (by: 2020-10-15)

===================
```

## Display all the task in a module on a date: `display  MODULECODE /date DATE`

  Display task in a specific module on a specific Date

  Format: `display  MODULECODE /date DATE`
  * The `DATE` must be in `YYYY/MM/DD`

  Example of Usage

  * `display CS2113 /date 2020/10/15`

```
===================
display CS2113 /date 2020/10/15
The list of task in CS2113 on 2020-10-15 :
[D]CS2113 Tp version 1 (by: 2020-10-15)

===================
```

## Display all the task on a date: `display /date DATE`

Display task on a specific Date
Format: `display /date DATE`

* The `DATE` must be in `YYYY/MM/DD`
  

Example of Usage
* `display /date 2020/10/15`

```
===================
display /date 2020/10/15
Here is your schedule on 2020-10-15!! :)
08:00
09:00
10:00
11:00
12:00
13:00 tutorial, CS2113 Tp version 1, CS2113
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

Deadlines on 2020-10-15:
You don't have any tasks!
===================
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
Format: `edit TASKNAME /date DATE /	/ATTRIBUTES /NEW_VALUE`

Example of Usage: 

* edit v1.0 /date 2021/04/12 /description /v2.0
* edit v1.0 /date 2021/04/12 /date /2021/02/01

###Delete all tasks of a description

Delete every task that fits the description regardless of date
Format: delete taskname 

Example of Usage

`delete v1.0`

###Delete all tasks of a description

Delete every task that fits the description on that particular date
Format: display /date DATE

* The DATE must be in YYYY/MM/DD

Example of Usage

* delete v1.0 /date 2020/01/12

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

