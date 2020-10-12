# User Guide

## Introduction

DueQuest is an application which helps NUS students manage their schedule in the semester
as well as tasks related to their modules that they are taking.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

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

## Adding a Task
Adds a new task into the schedule manager according to which day the task stated.
A Task can be a Lesson, Event or Deadline.
Lessons are classes that are held on every week in your timetable.
Events are one-off and they only take place on a single day.
Deadline are assignments where there is a due date.

### Adding a lesson: 'lesson'
Adds a new Lesson into both your Schedule Manager and Module Manager

Format: `lesson d/DESCRIPTION M/MODULE_CODE /on d/DAY f/FREQUENCY s/START_TIME e/END_TIME`

* The `DAY` is the Day Of Week when the lesson is conducted. Type in an integer. If the lesson is held on Thursday, type in 4. If the lesson is held on Wednesday, type in 3.
* The `START_TIME` and `END_TIME` are both in `HH:MM` format. You are only allowed to type in timings such that `MM` is `00`. For example, `18:00` is allowed but not `18:01`.

Examples of Usage:

* If you want to add a CS2113 online lecture lesson which starts at 4pm and ends at 6pm, held on every Friday (every 7 days): `lesson online lecture CS2113 /on 5 7 16:00 18:00`.

* If you want to add a CS1234 online tutorial lesson which starts at 12pm and ends at 2pm, held on every Thursday (every 7 days): `lesson online tutorial CS1234 /on 4 7 12:00 14:00`

### Adding an event: 'event'
Adds an Event into the Schedule Manager. If the Event is associated to a Module, it will be added to the Module Manager as well.

Format:

*
*

Examples of Usage:


### Adding a deadline: 'deadline'
Adds a deadline of an assignment into the Schedule Manager and Module Manager. 

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}
