# Developer Guide of DueQuest

## Table of contents

1. Introduction
    - Welcome to DueQuest
2. Setting Up
    - Prerequisites
    - Setting the project
3. Design
    - Architecture
    - Task Component
    - Command
    - Managers
    - Storage
4. Implementation
    - Adding
    - Display 
    - Storage
    - Edit
    - Delete
5. Documentation
    - Basic Thing to know
    - Style guidance
    - Diagrams
6. Testing
    - Running Tests
    - Types of Tests
7. Appendices
    - Appendix A: Product Scope
    - Appendix B: User Stories
    - Appendix C: Non Functional Requirements
    - Appendix D: Glossary
    - Appendix E: Instructions for Manual Testing
    

## 1. Introduction

### Welcome to DueQuest

Hello! We appreciate your interest in contributing to DueQuest. DueQuest is an application that
uses a Command Line Interface (CLI) specifically for NUS Students to manage their schedule
during school. This guide is written to give a clearer understanding for developers who are
interested in improving the design and implementation of DueQuest.

## 2.Setting Up

### Prerequisites
- JDK 11
- IntelliJ IDEA
### Setting the project

1. Fork the repo and clone it into your computer
2. Configure the JDK in IntelliJ to ensure it is configured to use JDK11.
3. Click `Import Project` (or `Open or Import` in newer version of Intellij).
4. IntelliJ IDEA by default has the Gradle plugin installed. If you have disabled it, go
to `File → Settings → Plugins` to re-enable them.
5. Locate the build.gradle file (not the root folder as you would do in a normal
importing) and select it. Click `OK` . If asked, choose to `Open as Project` (not `Open as
File` ).
6. Click `OK` to accept the default settings but do ensure that the selected version of
`Gradle JVM` matches the JDK being used for the project.
7. Wait for the importing process to finish (could take a few minutes).

## 3. Design

### Architecture
The diagram below shows a flow chart which gives an overall general picture of how the application works whenever the application receives an input from the user and a shows how each component interacts with one another for different scenarios. The logic behind the application is mostly handled by the `Parser` which converts inputs into various executable `Command`.

![](Images/3.1Archi.jpg)

### Module Component 

![](Module-4838589.jpg)



`ModuleManager` is the class that maintains the list of `Module` for the app and provides appropriate API to manipulate these modules. `Module` class has attributes such as `moduleCode`, `title`, `auNumber` and `teachingSatffs`, and it also keeps the records of `Assessment` (e.g. assignments, exams, etc.) 

To manipulate the module, the developer needs to access it from `ModuleManager`, generally through APIs such as `getModule(moduleCode: String)` . 

### Task Component

Each `Task` can be a `Lesson`, `Event` or `Deadline`. Below is a UML diagram showing
some of the properties and methods that these classes have. A `Task` will be created
whenever a User wants to add a Task into the `ScheduleManager` or `ModuleManager`.

![](Images/3.2Task.JPG)

| Class | Function |
|--------|----------|
| `Task` | Parent class of `Lesson`, `Deadline` and `Event` |
| `Lesson` | Represents an `Lesson` object. A `Lesson` object represents a lesson for a module that occurs during the semester, maintaining information such as the `description` of the lesson, `startTimeOfLesson`, `endTimeOfLesson`, and day of the week which it occurs on (which is noted by the `frequency`). |
| `Deadline` | Represents an `Event` object. An `Event` object represents a one-off event for a module that maintains information such as `date` of the event, `startTimeOfEvent`, `endTimeOfEvent`. |
| `Event` | Represents a `Deadline` object. A `Deadline` object represents a deadline for a module that maintains information such as `description` of the deadline and `date` of the deadline. |

### Command
The logic of what should be executed whenever the application receives an input will be
handled by parsing these inputs into Commands using a Parser . Below is a UML Diagram
showing how the `Parser` classes and the `Command` classes interact. Unless the `Command` is
an `ExitCommand` or `HelpCommand` , the `Command` will be executed to perform an action to
either the `ScheduleManager` or `ModuleManager` or both.

![](Images/3.3Command.JPG)

| Class | Function |
|--------|----------|
| `Command` | An Abstract class which is the parent class of all of the commands below. The main method in this class is the `execute()` method, which handles the different executions required depending on the input by the user. |
| `AddCommand` | A child class of `Command` which helps to execute the feature of adding a `Task` into the `ScheduleManager` and `ModuleManager`. |
| `AddModuleCommand` | A child class of `Command` which helps to add a `Module` into the `ModuleManager`. |
| `DeleteCommand` | -- |
| `DisplayCommand` | A child class of `Command` which helps to display the list of tasks in a any day stated by the user, or the list of tasks from a `Module` stated by the user. |
| `EditModuleCommand` | -- |
| `EditTaskCommand` | -- |
| `HelpCommand` | A child class of `Command` which helps to provides the list of inputs for the user to know what to type in to use any of the features he wants. |
| `ExitCommand` | A child class of `Command` which helps to exit the app. |
| `AddAssessmentCommand` | A child class of `Command` which adds assessment to the module. |
| `ScoreAssessmentCommand` | A child class of `Command` which adds actual score to the assessment. |
| `DeleteAssessmentCommand` | A child class of `Command` which deletes the assessment from the module. |

### Managers
The application consists of two managers, the `ScheduleManager` and `ModuleManager`. The
`ScheduleManager` will handle the storing of `Task` in each day. The `ModuleManager` will
handle storing the task of each `Module`. Below is a UML Diagram showing some of the key
methods and properties of the `ScheduleManager` and `ModuleManager` classes.

![](Images/3.4Managers.JPG)

| Class | Function |
|--------|----------|
| `ScheduleManager` | The `ScheduleManager` class  helps to store the `Task`s in each day. |
| `ModuleManager` | `ModuleManager` class helps to store the `Task`s of each module. |

Some Design Considerations on how to store the `Task` in the `ScheduleManager`:
- Use of a `TreeMap` to store the date as keys and `ArrayList` as values.
    - Pros: Use of a `TreeMap` allows iteration of dates as the key to be iterated in order, which
                might be useful in further development of implementation of the use of the `ScheduleManager`.
                Searching for a date can also be done in O(logn) similarly to a `HashMap`.
    - Cons: Storing the dates that have past and also storing of many dates might be heavy in
                terms of space.
- Use of a `HashMap` to store the date as keys and `ArrayList` as values
    - Pros: Allows tracking of tasks with respect to the dates as keys.
    - Cons: Does Not allow iteration in order as when desired as compared to a `TreeMap`.
- Creation of a new class which contains a schedule for each day, and using a `TreeSet`.
      to contain this new class.
    - Pros: Making use of more oop design.
    - Cons: Might be tricky to implement and may need more resources.
  
### Storage
The application will save all of the user inputs by using a `Storage` class, which is designed as
singleton.

![](Images/3.5Stroage.JPG)

| Class | Function |
|--------|----------|
| `Storage` | It exports the data in the application and converts it into the form of a txt file. This allows users to retain their saved data after exiting the app. More elaboration of how this will be done will be shown later, where a sequence diagram will show how this process works. |

## 4. Implementation

This section describes some noteworthy details on how certain features are implemented.

### Add Feature

The add implementation allows the user to add a `Lesson`, `Event` or `Deadline`. This is facilitated
by implementing the ScheduleManager and ModuleManager, which stores all of the `Task` in
them. The `ScheduleManager` helps to save these tasks into the dates according to the date of
the `Task` as stated by the User. The `ModuleManager` will also add the `Task` into the respective module
that it belongs to. Below is a sequence diagram which shows how adding of a
`Lesson` works. Adding of a Event or a Deadline is also in a similar fashion.

![](Images/4.1Add.JPG)

1. The user inputs `lesson online lecutre CS2113 /on 5 16:00 18:00`, which will be by read by the `Ui` by it's `readCommand()` method, and returned as a String variable called `fullCommand`.
1. A `Parser` is then used to obtain the logic behind the user's input. The `Parser` will first use it's `parse(fullCommand)` method, which will then call the `parseLesson(fullCommand)` method after finding out that the user wants to add a lesson since that was the first word in the user input for this case.
1. The `parseLesson(fullCommand)` method will return a `Lesson lesson` object. It will also call a `AddCommand`, which will contain the `Lesson lesson` object, and the `AddCommand` will be returned to the `DueQuest` main class.
1. Next, the `AddCommand` will be executed to handle the logic of adding a lesson with the details as stated by the user's input. 
1. Finally, the `execute(ScheduleManager scheduleManager, ModuleManager moduleManager)` will first call the `ScheduleManager`, which calls its own `addLesson(lesson, moduleManager, ui)` method to add the lesson to itself. It also calls the `ModuleManager` object, and the `ModuleManager` will call its own `addTaskToModule(lesson, moduleCodeOfLesson)` to add the lesson to the `Module` in it which has the module code stated by the user.


### Display Feature

![](Images/4.2Display.JPG)

For display method, there are 5 possible scenarios that could include parameters such
as `Module` code, date & range of date.
As seen from the diagram, the 5 Scenarios are:
1. Display today Schedule
2. Display all information in a specific module
3. Display all task in a specific module on a specific date
4. Display all task within a range of dates
5. Display all task on a specific date

Example: flow for Displaying today schedule:
1. The user will run the app and type in “display”, which will then be received by the `Ui` and
it returns the input as a String fullCommand
2. The String fullCommand will then be parsed by a `Parser` to return an `DisplayCommand`
object.
3. The DisplayCommand object will then be executed to display today’s Schedule using the
`ScheduleManager.displayToday()` method

### Storage Feature

The storage is implemented in singleton such that the `Storage` class holds only 1 private instance, the constructor of which (e.g. `Storage(directoryPath)`) is private. Such instance can only be created with the class method, `Storage.setUpStorage(directoryPath)`. 

1. Set up the `Storage` from local disk 

![](Images/4.3Storage1.JPG)

2. Add/Edit Module and Its Components
When modules’ information or their components are changed (e.g. add, delete the module or add, delete the assessments), the changed module’s code will be passed to storage. `Storage` will export the new information of the changed `Module` to the corresponding local files.

![](Images/4.3Storage2.JPG)

3. Edit/Delete Action

There are two types of edit/deleting:
-  without module specified 
    ![](Images/4.3Storage3.JPG)
    Once some tasks are deleted, the `ModuleManager` is updated. Storage will exported
    the new content of `ModuleManager` by iterating all modules in `ModuleManager` , since the module is not specified.
    
- with module specified 
    ![](Images/4.3Storage4.JPG)
    The command of edit/deleting is passed to `Storage`, and `Storage` will write this `Command`
    to `AdditionalFile`, so that whenever importing files, this `DeleteCommand` will be
    executed again from `AdditionalFile`.

### Edit Feature

The `EditCommand` base class forms the basis for the two extensions listed below.

1. Edit Module Command: When the `EditModuleCommand` is executed, it checks for the description of the particular property to 
edit with `ScheduleManager` and `ModuleManager`.  The constructor function is overloaded such that the 
conditions will check for which property to edit. In the case that there are no module details to modify,
the function execution will go on to check for module tasks to modify.

![](Images/5.1EditModule1.jpeg)

2. Edit Task Command: When the `EditTaskCommand` is executed, it checks for the description of the particular property to 
edit with `ScheduleManager` and `ModuleManager`.  The constructor function is overloaded such that the 
conditions will check for which property to edit.

![](Images/5.2EditTask2.jpeg)

### Delete Feature

The `DeleteCommand` is the class which oversees the deletion of modules and tasks.

1. The `DeleteCommand` class is overloaded such that the conditions of the deletion are checked. The 
`ScheduleManager` and `ModuleManager` both manage their deletions separately, overloading the deletions
where necessary.

![](Images/6.1Delete.jpeg)

## 5. Documentation

This Section describes how to write documentation for the project. The projects is
written in GitHub-Flavoured Markdown

### Basic Thing to know
- The docs/ folder is used to store documentation file

### Style guidance
- Google developer Documentation style guide
- [se-edu/guides] Markdown coding standards

### Diagrams
- Draw.io ( free )

## Testing

## 6. Running Tests

### Running Tests
There are two ways to run tests
1. Using IntelliJ JUnit Test Runner
    1. To run all the test, right click on the src/test/java and choose Run ‘Tests in
tp.test’
    2. To run a subset of test, you can right click on a test package, test class, or a test
and choose run “ScheduleManagerTest”
2. Using Gradle
    1. Open a console and run the command gradlew clean test
    2. For Mac or linux users, use ./gradlew clean test
    
### Types of Tests
For this project, we are using one type of test:
- Unit Test.
- For eg, seedu.duequest.ModuleManagerTest.

Appendices
Appendix A: Product Scope
Appendix B: User Stories
Appendix C: Non Functional Requirements
Appendix D: Glossary
APpendix E: Instructions for Manual Testing

## 7. Appendices

### Appendix A: Product Scope 

#### Target user profile

The target user for our app is for:
 - NUS Students who hope to have a platform for them to manage their schedule and work in the school semester.
 - Users who can type fast.
 - Users who are reasonably comfortable using CLI applications. 

#### Value proposition

To help NUS Students by keeping them on track with their schedule and work on a daily basis using a CLI driven app.
Thus, reducing the chances of them forgetting about any work related to school.

### Appendix B: User Stories 

![](Images/userstories.PNG)
This is an example of how we originally tackle the user stories.

* The green or orange highlight is to group similar features together
* The Strikethrough is when a feature is redundant or unneccessary at the stage of planning

### Appendix C: Non Functional Requirements

1. Should work on any *mainstream OS* as long as it has Java `11` or above installed.
1. Should be quick enough to take in input from the user and show output for the user.
1. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should 
be able to accomplish most of the tasks faster using commands than using the mouse.

### Appendix D: Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X

### Appendix E: Instructions for Manual Testing

Given below are instructions to test the app manually.

#### Initial Launch
1. Download the latest executable jar file from our latest release.
1. Copy the jar file into an empty folder.
1. Run the program using the command line by inputting `java -jar DueQuestv2.1jar`.
1. You should see the following below



#### Adding a module

#### Positive Test
1. Enter `module c/CS2113 a/4 s/Dr.Akshay s/ChengChen` into the console and you should see the following below:

#### Negative Test 1: Duplicate module code
1. Enter `module c/CS2113 a/4 s/Dr.Akshay s/ChengChen` again into the console and you should see the following below:

#### Negative Test 2: Invalid module code
1. Enter `module c/cs2113 a/4 s/Dr.Akshay s/ChengChen` into the console and you should see the following below:

#### Negative Test 3: au not specified
1. Enter `module c/ST2113` into the console and you should see the following below:

#### Adding Tasks.

There are three types of tasks to add: `Lesson`, `Event` and `Deadline`

#### Adding a Lesson

#### Positive Test
1. Enter `lesson online lecture CS2113 /on 5 16:00 18:00` into the console and you should see the following below:

1. Enter `display CS2113` into the console and you should see if the lesson has been indeed added to the module. You should see the following below:

#### Negative Test 1: A module code that does not exist in the ModuleManager
1. Enter `lesson online lecture MA2113 /on 5 16:00 18:00` into the console and you should see the following below:

#### Negative Test 2: Invalid Start Time 
1. Enter `lesson online lecture CS2113 /on 5 16:aa 18:00` into the console and you should see the following below:

#### Negative Test 3: Invalid frequency
1. Enter `lesson online lecture CS2113 /on 8 16:00 18:00` into the console and you should see the following below:


#### Adding an Event

#### Positive Test
1. Enter `event CS2113 final exam /at 2021-05-03 14:00 16:00 LT14` into the console and you should see the following below:

1. Enter `display /date 2021/05/03` into the console and to see that the event has been indeed added to the date. You should see the following below:

1. Enter `display CS2113` into the console and to see that the event has been indeed added to the module. You should see the following below:


#### Negative Test 1: A module code that does not exist in the ModuleManager
1. Enter `event MA3333 final exam /at 2021-05-03 14:00 16:00 LT14` into the console and you should see the following below:

#### Negative Test 2: A date that is out of range (meaning not between 1 January 2021 and 31 May 2021)
1. Enter `event CS2113 final exam /at 2021-06-01 14:00 16:00 LT14` into the console and you should see the following below:

#### Negative Test 3: A start time that is too early
1. Enter `event CS2113 final exam /at 2021-05-01 07:00 10:00 LT14` into the console and you should see the following below:


#### Adding an Deadline

#### Positive Test
1. Enter `deadline CS2113 TP version 1 /by 2021-04-04` into the console and you should see the following below:

1. Enter `display /date 2021/04/04` into the console and to see that the deadline has been indeed added to the date. You should see the following below:

1. Enter `display CS2113` into the console to see that the deadline has been indeed added to the module. You should see the following below:

#### Negative Test 1: deadline with empty description
1. Enter `deadline CS2113  /by 2021-04-04` into the console and you should see the following below:

#### Negative Test 2: deadline with invalid date format
1. Enter `deadline CS2113 TP version 1 /by 2021-4-4` into the console and you should see the following below:

#### Adding an Assessment

#### Positive Test
1. Enter `assessment CS2113 TP 100` into the console and you should see the following below:

1. Enter `display CS2113` into the console to see if the assessment has been indeed added. You should see the following below:

#### Negative Test 1: Missing assessment title name
1. Enter `assessment CS2113 100` into the console and you should see the following below:

#### Add score to an assessment

#### Positive Test
1. Enter `score CS2113 TP 100` into the console and you should see the following below:

1. Enter `display CS2113` into the console to see if the score of the assessment has been indeed added. You should see the following below:

#### Negative Test 1: Adding score to an assessment with a title that does not exist in the module.
1. Enter `score CS2113 aa 100` into the console and you should see the following below:

#### Negative Test 2: Adding score to an assessment to a module that does not exist
1. Enter `score CT2113 TP 100` into the console and you should see the following below:

#### Delete an assessment

#### Positive Test
1. Enter `delete_assessment CS2113 TP` into the console and you should see the following below:

1. Enter `display CS2113` into the console to see if the assessment has been indeed deleted. You should see the following below:


#### Negative Test 1: Deleting of an assessment that does not exist
1. Enter `delete_assessment CS2113 aa` into the console and you should see the following below:

#### Display

#### Positive Test
1. Enter `display CS2113` into the console and you should see the following below:

#### Negative Test 1: Module code that does not exist
1. Enter `display CS2113T` into the console and you should see the following below:

#### Display all the task in a module on a date

#### Positive Test
1. Enter `display CS2113 /date 2021/04/04` into the console and you should see the following below:

#### Negative Test 1: Invalid date
1. Enter `display CS2113 /date 2021/04/0a` into the console and you should see the following below:


#### Display all the task on a date

#### Positive Test
1. Enter `display /date 2021/05/03` into the console and you should see the following below:


#### Negative Test 1: Invalid Date: Date not between 1 January 2021 and 31 May 2021
1. Enter `display /date 2020/12/31` into the console and you should see the following below:

#### Display all the task on a range of date

#### Positive Test
1. Enter `display /date 2021/05/02-2021/05/05` into the console and you should see the following below:

#### Negative Test 1: Invalid date format
1. Enter `display /date 2021/00/02-2021/06/05` into the console and you should see the following below:



#### Editing a task
**Important! Please do the following before carrying on with the rest of the tests for this section.**
1. Enter `delete c/CS2113` into the console and you should see the following below:
1. Enter `module c/CS2113 a/4` to add the module and you should see the following below:


#### Edit the date of a task with description

#### Positive Test
1. Enter `deadline CS2113 tp /by 2021-04-20` into the console and you should see the following below:

1. Enter `edit tp /date 2021-04-20 /date /2021-04-21` into the console and you should see the following below:

1. Enter `display CS2113` into the console to check if the date of the task tp has been indeed changed in the module, and you should see the following below:

1. Enter `display /date 2021/04/21` into the console to check if the date of the task tp has been indeed changed in the date, and you should see the following below:


#### Negative Test 1: Invalid module code
1. Enter `edit c/CS2114 tp /date 2021-04-21 /date /2021-04-20` into the console and you should see the following below:

#### Edit the date of a task with description and module code

#### Positive Test
*Note: We carry on from the previous example, so there exist a task on 2021/04/21 tp from before*
1. Enter `edit c/CS2113 tp /date 2021-04-21 /date /2021-04-20` into the console and you should see the following below:

1. Enter `display CS2113` into the console to check if the date of the task tp has been indeed changed in the module, and you should see the following below:
   
1. Enter `display /date 2021/04/21` into the console to check if the date of the task tp has been indeed changed in the date, and you should see the following below:

#### Negative Test 1: Invalid module code
1. Enter `edit c/CS2114 tp /date 2021-04-21 /date /2021-04-20` into the console and you should see the following below:

#### Deleting tasks
**Important! Please do the following before carrying on with the rest of the tests for this section.**
1. Enter `delete c/CS2113` into the console and you should see the following below:
1. Enter `module c/CS2113 a/4` to add the module and you should see the following below:

#### Deleting task via description

#### Positive Test
1. Enter `deadline CS2113 tp /by 2021-04-20` into the console and you should see the following below:

1. Enter `delete tp` into the console and you should see the following below:

1. Enter `display CS2113` into the console to check if the date of the task tp has been indeed deleted in the module, and you should see the following below:
   
1. Enter `display /date 2021/04/20` into the console to check if the date of the task tp has been indeed deleted in the date, and you should see the following below:

#### Negative test 1: deleting task with description that does not exist in the app
1. Add back the deadline by entering `deadline CS2113 tp /by 2021-04-20` into the console and you should see the following below:

1. Enter `delete tpp` into the console and you should see the following below:

#### Deleting task via description and date
*Note: Remember that there is already a deadline that was not deleted in the earlier part*

#### Positive test
1. Enter `delete tp /date 2021-04-20` into the console and you should see the following below:

1. Enter `display CS2113` into the console to check if the date of the task tp has been indeed deleted in the module, and you should see the following below:
   
1. Enter `display /date 2021/04/20` into the console to check if the date of the task tp has been indeed deleted in the date, and you should see the following below:

#### Negative Test 1:Invalid date
1. Add back the deadline by entering `deadline CS2113 tp /by 2021-04-20` into the console and you should see the following below:

1. Enter `delete tp /date 2021-04-aa` into the console and you should see the following below:


#### Delete all of a module's task on a certain date
*Note: Remember that there is already a deadline that was not deleted in the earlier part*

1. Add another deadline by entering `deadline CS2113 tp jar file /by 2021-04-20` into the console and you should see the following below:

1. Enter `delete c/CS2113 /date 2021-04-20` into the console and you should see the following below:

1. Enter `display CS2113` into the console to check if the date of the task tp has been indeed deleted in the module, and you should see the following below:
   
1. Enter `display /date 2021/04/20` into the console to check if the date of the task tp has been indeed deleted in the date, and you should see the following below:

#### Negative Test 1: Invalid date
1. Add back the deadline by entering `deadline CS2113 tp /by 2021-04-20` into the console and you should see the following below:

1. Enter `delete c/CS2113 /date 2021-04-2a` into the console and you should see the following below:


#### Delete all of a module's task's with fitting description, on a certain date
*Note: Remember that there is already a deadline that was not deleted in the earlier part*

#### Positive Test

1. Add another deadline by entering `deadline CS2113 tp dg /by 2021-04-25` into the console and you should see the following below:

1. Enter `delete c/CS2113 tp /date 2021-04-20` into the console and you should see the following below:

1. Enter `display CS2113` into the console to check if the date of the task tp has been indeed deleted in the module, and you should see the following below:
   
1. Enter `display /date 2021/04/20` into the console to check if the date of the task tp has been indeed deleted in the date, and you should see the following below:

#### Negative Test: Invalid date
*Note: Remember that you still have the deadline tp dg by 2021-04-25 in the app*

1. Enter `delete c/CS2113 tp /date 2021-04-2a` into the console and you should see the following below:

#### Deleting entire module

#### Positive Test

1. Enter `delete c/CS2113` into the console and you should see the following below:

1. Enter `display CS2113` into the console to check if the date of the task tp has been indeed deleted in the module, and you should see the following below:

#### Negative Test: Module code that does not exist in the app

1. Add the module back by entering `module c/CS2113 a/4` into the console and you should see the following below:

1. Enter `delete c/CS2113T` into the console and you should see the following below:

#### Exiting the app

#### Positive Test

1. Enter `bye` into the console and you should see the following below:

#### Negative Test 1: Invalid input

1. Enter `byee` into the console and you should see the following below:
