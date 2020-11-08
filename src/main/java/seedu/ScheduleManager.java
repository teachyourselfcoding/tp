package seedu;

import seedu.exception.InvalidStartEndDateException;
import seedu.exception.InvalidDateException;
import seedu.exception.ModuleDoesNotExistException;
import seedu.exception.StartAndEndTimeSameException;
import seedu.task.Deadline;
import seedu.task.Event;
import seedu.task.Lesson;
import seedu.task.Task;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * READ THIS FIRST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!.
 * Class for ScheduleManager.
 * Contains the schedule for the user.
 * We will assume that the ScheduleManager is built for AY 2020/2021 Semester 2.
 * NonLessonDates includes - Winter Break, Reading weeks, Examination week.
 * Dates of the following are obtained from NUS website.
 */
public class ScheduleManager {
    private TreeMap<LocalDate, ArrayList<Task>> semesterSchedule = new TreeMap<>();
    private HashSet<LocalDate> listOfNonLessonDates = new HashSet<>();

    /**
     * Constructor for ScheduleManager if a ScheduleManager already exist.
     *
     * @param semesterSchedule treeMap object of Local Date and List of Task
     */
    public ScheduleManager(TreeMap<LocalDate, ArrayList<Task>> semesterSchedule) {
        this.semesterSchedule = semesterSchedule;
    }

    public TreeMap<LocalDate, ArrayList<Task>> getSemesterSchedule() {
        return semesterSchedule;
    }

    public void setSemesterSchedule(TreeMap<LocalDate, ArrayList<Task>> semesterSchedule) {
        this.semesterSchedule = semesterSchedule;
    }

    /**
     * Constructor for ScheduleManager if a ScheduleManager has yet to be created.
     * Main role is to populate the HashMap with the dates in the year as the 'key'
     * and an empty list of task as the value.
     */
    public ScheduleManager() {
        this.semesterSchedule = new TreeMap<>();
        for (LocalDate date = LocalDate.of(2020, 10, 12);
             date.isBefore(LocalDate.of(2021, 6, 1));
             date = date.plusDays(1)) {
            this.semesterSchedule.put(date, new ArrayList<>());
        }
        for (LocalDate date = LocalDate.of(2020, 12, 6);
             date.isBefore(LocalDate.of(2021, 1, 11));
             date = date.plusDays(1)) {
            this.listOfNonLessonDates.add(date);
        }
        for (LocalDate date = LocalDate.of(2021, 2, 20);
             date.isBefore(LocalDate.of(2021, 3, 1));
             date = date.plusDays(1)) {
            this.listOfNonLessonDates.add(date);
        }
        for (LocalDate date = LocalDate.of(2021, 4, 17);
             date.isBefore(LocalDate.of(2021, 5, 9));
             date = date.plusDays(1)) {
            this.listOfNonLessonDates.add(date);
        }
        for (LocalDate date = LocalDate.of(2021, 5, 9);
             date.isBefore(LocalDate.of(2021, 6, 1));
             date = date.plusDays(1)) {
            this.listOfNonLessonDates.add(date);
        }
    }

    /**
     * Add lessons to the day of the week that the lesson is conducted in.
     *
     * @param lesson lesson to be added to the schedule manager.
     */
    public void addLesson(Lesson lesson, ModuleManager moduleManager, Ui ui) throws ModuleDoesNotExistException {
        DayOfWeek day = lesson.getLessonDayInDayOfWeek();
        if (!moduleManager.getListOfModuleCodes().contains(lesson.getModuleCode())) {
            throw new ModuleDoesNotExistException();
        }
        if (checkIfLessonToBeAddedClashesWithCurrentTimetable((lesson))) {
            ui.printClashesMessage();
            return;
        }
        for (Map.Entry<LocalDate, ArrayList<Task>> entry : this.semesterSchedule.entrySet()) {
            LocalDate key = entry.getKey();
            if (!this.listOfNonLessonDates.contains(key)) {
                if (key.getDayOfWeek().getValue() == day.getValue()) {
                    this.semesterSchedule.get(key).add(lesson);
                }
            }
        }
        moduleManager.addTaskToModule(lesson, lesson.getModuleCode());
        Ui.printLessonAddedMessage();
    }

    public boolean checkIfLessonToBeAddedClashesWithCurrentTimetable(Lesson lesson) {
        DayOfWeek day = lesson.getLessonDayInDayOfWeek();
        for (Map.Entry<LocalDate, ArrayList<Task>> entry : this.semesterSchedule.entrySet()) {
            LocalDate key = entry.getKey();
            if (!this.listOfNonLessonDates.contains(key)) {
                if (key.getDayOfWeek().getValue() == day.getValue()) {
                    if (checkIfLessonToBeAddedClashesInADate(lesson, key)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkIfLessonToBeAddedClashesInADate(Lesson lesson, LocalDate date) {
        ArrayList<Task> listOfTasks = this.semesterSchedule.get(date);
        LocalTime startTimeOfLesson = lesson.getStartTimeInLocalTime();
        LocalTime endTimeOfLesson = lesson.getEndTimeInLocalTime();
        for (Task task : listOfTasks) {
            if (task instanceof Lesson) {
                LocalTime startTimeOfTask = ((Lesson) task).getStartTimeInLocalTime();
                LocalTime endTimeOfTask = ((Lesson) task).getEndTimeInLocalTime();
                if (startTimeOfLesson.equals(endTimeOfTask) || startTimeOfLesson.isAfter(endTimeOfTask)) {
                    //break;
                    continue;
                }
                if (endTimeOfLesson.equals(startTimeOfTask) || endTimeOfLesson.isBefore(startTimeOfTask)) {
                    //break;
                    continue;
                }
                return true;
            }
            if (task instanceof Event) {
                LocalTime startTimeOfTask = ((Event) task).getStartTimeOfEventInLocalTime();
                LocalTime endTimeOfTask = ((Event) task).getEndTimeOfEventInLocalTime();
                if (startTimeOfLesson.equals(endTimeOfTask) || startTimeOfLesson.isAfter(endTimeOfTask)) {
                    //break;
                    continue;
                }
                if (endTimeOfLesson.equals(startTimeOfTask) || endTimeOfLesson.isBefore(startTimeOfTask)) {
                    //break;
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    public boolean checkIfEventToBeAddedClashesInADate(Event event, LocalDate date) {
        ArrayList<Task> listOfTasks = this.semesterSchedule.get(date);
        LocalTime startTimeOfEvent = event.getStartTimeOfEventInLocalTime();
        LocalTime endTimeOfEvent = event.getEndTimeOfEventInLocalTime();
        for (Task task : listOfTasks) {
            if (task instanceof Lesson) {
                LocalTime startTimeOfTask = ((Lesson) task).getStartTimeInLocalTime();
                LocalTime endTimeOfTask = ((Lesson) task).getEndTimeInLocalTime();
                if (startTimeOfEvent.equals(endTimeOfTask) || startTimeOfEvent.isAfter(endTimeOfTask)) {
                    //break;
                    continue;
                }
                if (endTimeOfEvent.equals(startTimeOfTask) || endTimeOfEvent.isBefore(startTimeOfTask)) {
                    //break;
                    continue;
                }
                return true;
            }
            if (task instanceof Event) {
                LocalTime startTimeOfTask = ((Event) task).getStartTimeOfEventInLocalTime();
                LocalTime endTimeOfTask = ((Event) task).getEndTimeOfEventInLocalTime();
                if (startTimeOfEvent.equals(endTimeOfTask) || startTimeOfEvent.isAfter(endTimeOfTask)) {
                    //break;
                    continue;
                }
                if (endTimeOfEvent.equals(startTimeOfTask) || endTimeOfEvent.isBefore(startTimeOfTask)) {
                    //break;
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    /**.
     * Deadline only got 1 day, so just filter for the
     * date where I need to add the deadline,
     *
     * @param deadline add deadline inside the list of tasks of the schedule manager.
     */
    public void addDeadline(Deadline deadline, ModuleManager moduleManager) throws ModuleDoesNotExistException {
        if (!moduleManager.getListOfModuleCodes().contains(deadline.getModuleCode())) {
            throw new ModuleDoesNotExistException();
        }
        LocalDate date = LocalDate.parse(deadline.getDeadline());
        this.semesterSchedule.get(date).add(deadline);
        moduleManager.addTaskToModule(deadline, deadline.getModuleCode());
    }

    /**
     * Event only got 1 date, so just filter for the
     * date where I need to add the event.
     *
     * @param event add event inside the list of tasks of the schedule manager.
     */
    public void addEvent(Event event, ModuleManager moduleManager, Ui ui) throws ModuleDoesNotExistException {
        LocalDate date = LocalDate.parse(event.getDateOfEvent());
        if (!moduleManager.getListOfModuleCodes().contains(event.getModuleCode())) {
            throw new ModuleDoesNotExistException();
        }
        if (checkIfEventToBeAddedClashesInADate(event, date)) {
            ui.printClashesMessage();
            return;
        }
        this.semesterSchedule.get(date).add(event);
        if (!event.getModuleCode().equals("")) {
            moduleManager.addTaskToModule(event, event.getModuleCode());
        }
        if (!event.getModuleCode().equals("")) {
            Ui.printEventAddedMessage();
        } else {
            Ui.printEventAddedtoScheduleOnlyMessage();
        }
    }


    public void editTask(String description, LocalDate date, String type, String newProperty, String moduleCode) {
        //edit property of module task on a certain date
        for (Task task : semesterSchedule.get(date)) {
            switch (type) {
            case "description":
                if (task.getDescription().equals(description) && task.getModuleCode().equals(moduleCode)) {
                    task.setDescription(newProperty);
                }
                break;
            case "tasktype":
                if (task.getDescription().equals(description) && task.getModuleCode().equals(moduleCode)) {
                    task.setTasktype(newProperty);
                }
                break;

            case "module code":
                if (task.getDescription().equals(description) && task.getModuleCode().equals(moduleCode)) {
                    task.setModulecode(newProperty);
                }
                break;
            case "time":

                if (task.getDescription().equals(description) && task.getModuleCode().equals(moduleCode)) {
                    task.setTime(newProperty);
                }
                break;
            default:
                Ui.printInvalidEditTypeMessage();
            } Ui.printModuleTaskEditedMessage();
        }
    }

    public void editTask(String description, LocalDate date, String type, String newProperty) {
        //edit property of task on a certain date
        boolean edited = false;
        for (Task task : semesterSchedule.get(date)) {
            switch (type) {
            case "description":
                if (task.getDescription().equals(description)) {
                    task.setDescription(newProperty);
                    edited = true;
                }
                break;
            case "tasktype":
                if (task.getDescription().equals(description)) {
                    task.setTasktype(newProperty);
                    edited = true;
                }
                break;

            case "module code":
                if (task.getDescription().equals(description)) {
                    task.setModulecode(newProperty);
                    edited = true;
                }
                break;
            case "time":

                if (task.getDescription().equals(description)) {
                    task.setTime(newProperty);
                    edited = true;
                }
                break;
            default:
                Ui.printInvalidEditTypeMessage();
                return;
            }
        }
        if (edited) {
            Ui.printSuccessfulEdit();
            return;
        }
        Ui.printTaskNotEditedMessage();
    }


    public void editTask(String description, LocalDate date, String property, int newFrequency, String moduleCode) {
        //edit frequency of module's task
        for (Task task : semesterSchedule.get(date)) {
            if (task.getDescription().equals(description) && task.getModuleCode().equals(moduleCode)) {
                task.setFrequency(newFrequency);
            }
        }
    }


    public void editTask(String description, LocalDate date, String property, int newFrequency) {
        //edit frequency of task
        boolean edited = false;
        for (Task task : semesterSchedule.get(date)) {
            if (task.getDescription().equals(description)) {
                task.setFrequency(newFrequency);
                edited = true;
            }
        }
        if (edited) {
            Ui.printTaskFrequencyEditedMessage();
            return;
        }
        Ui.printTaskFrequencyNotEditedMessage();
    }


    public void editTask(String description, LocalDate date, String property, LocalDate newDate, String moduleCode) {
        //edit date of module task
        boolean edited = false;
        for (Task task : semesterSchedule.get(date)) {
            if (task.getDescription().equals(description) && task.getModuleCode().equals(moduleCode)) {
                semesterSchedule.get(newDate).add(task);
                edited = true;
            }
        }
        if (edited) {
            for (Task newTask : semesterSchedule.get(newDate)) {
                if ((newTask.getDescription().equals(description)) && newTask.getModuleCode().equals(moduleCode)) {
                    newTask.setDate(newDate.toString()); //Need to change later
                }
            }
            deleteTask(description, date);
            return;
        }
    }

    public void editTask(String description, LocalDate date, String property, LocalDate newDate) {
        //edit date of  task
        boolean edited = false;
        for (Task task : semesterSchedule.get(date)) {
            if (task.getDescription().equals(description)) {
                semesterSchedule.get(newDate).add(task);
                edited = true;
            }
        }
        if (edited) {
            for (Task newTask : semesterSchedule.get(newDate)) {
                if (newTask.getDescription().equals(description)) {
                    newTask.setDate(newDate.toString()); //Need to change later
                }
            }
            deleteTask(description, date);
            Ui.printTaskDateEditedMessage();
            return;
        }
        Ui.printTaskDateNotEditedMessage();
    }



    public void deleteTask(String description, LocalDate date) {
        //delete all tasking matching description on date
        boolean deleted = false;
        for (Task task : semesterSchedule.get(date)) {
            if (semesterSchedule.get(date).size() != 0) {
                if (task.getDescription().equals(description)) {
                    deleted = true;
                }
            }
        }
        if (deleted) {
            if (semesterSchedule.get(date).size() != 0) {
                semesterSchedule.get(date).removeIf(task -> task.getDescription().equals(description));
            }
            Ui.printTaskDeletedMessage();
            return;
        }
        Ui.printTaskNotDeletedMessage();
    }

    public void deleteTask(String moduleCode, String description, LocalDate date) {
        //delete all task matching description and module code on date
        boolean deleted = false;
        if (semesterSchedule.get(date).size() != 0) {
            for (Task task : semesterSchedule.get(date)) {
                if (task.getModuleCode().equals(moduleCode) && task.getDescription().equals(description)) {
                    deleted = true;
                }
            }
            if (deleted) {
                Ui.printTaskDeletedMessage();
                semesterSchedule.get(date).removeIf(task -> task.getModuleCode().equals(moduleCode)
                        && task.getDescription().equals(description));
            } else {
                Ui.printTaskNotDeletedMessage();
            }
        }
    }

    public void deleteTask(LocalDate date, String moduleCode) { //delete all tasking matching  module code on date
        boolean deleted = false;
        if (semesterSchedule.get(date).size() != 0) {
            for (Task task : semesterSchedule.get(date)) {
                if (task.getModuleCode().equals(moduleCode)) {
                    deleted = true;
                }
            }
            if (deleted) {
                Ui.printTaskDeletedMessage();
                semesterSchedule.get(date).removeIf(task -> task.getModuleCode().equals(moduleCode));
            } else {
                Ui.printTaskNotDeletedMessage();
            }
        }
    }

    public void deleteTask(String description, String moduleCode) { //delete all tasking matching module code
        boolean deleted = false;
        for (LocalDate date = LocalDate.of(2020, 10, 12);
             date.isBefore(LocalDate.of(2021, 6, 1)); date = date.plusDays(1)) {
            if (semesterSchedule.get(date).size() != 0) {
                for (Task task : semesterSchedule.get(date)) {
                    if (task.getModuleCode().equals(moduleCode)) {
                        deleted = true;
                    }
                }
                semesterSchedule.get(date).removeIf(task -> task.getModuleCode().equals(moduleCode));
            }
        }
        if (!deleted) {
            Ui.printTaskModuleCodeNotDeletedMessage();
            return;
        }
        Ui.printTaskModuleCodeDeletedMessage();
    }

    public void deleteTask(String description) { //delete all task matching description
        boolean deleted = false;
        for (LocalDate date = LocalDate.of(2020, 10, 12);
             date.isBefore(LocalDate.of(2021, 6, 1)); date = date.plusDays(1)) {
            if (semesterSchedule.get(date).size() != 0) {
                for (Task task : semesterSchedule.get(date)) {
                    if (task.getDescription().equals(description)) {
                        deleted = true;
                    }
                }
                if (!deleted) {
                    Ui.printTaskNotDeletedMessage();
                    return;
                }
                semesterSchedule.get(date).removeIf(task -> task.getDescription().equals(description));
            }
        }
        Ui.printTaskDeletedMessage();
    }

    public void displayTodaySchedule() {
        LocalDate todayDate = LocalDate.now();
        Ui.print("Today's Schedule:");
        ArrayList<Task> taskList = semesterSchedule.get(todayDate);
        ArrayList<Task> nonLessonList = new ArrayList<>();
        String[] timing = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
            "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
        for (Task t : taskList) {
            if (t instanceof Lesson) {
                String startTime = ((Lesson) t).getStartTime();
                String endTime = ((Lesson) t).getEndTime();
                boolean hasStart = false;
                boolean hasEnd = false;
                for (int i = 0; i < timing.length; i++) {
                    if (timing[i].substring(0, 5).equals(startTime)) {
                        hasStart = true;
                        timing[i] = timing[i] + " " + t.getDescription() + " - " + t.getModuleCode() + " |";
                    } else if (timing[i].substring(0, 5).equals(endTime)) {
                        hasEnd = false;
                        hasStart = false;
                        break;
                    } else if (hasStart && !hasEnd) {
                        timing[i] = timing[i] + " " + t.getDescription() + " - " + t.getModuleCode() + " |";
                    }
                }
            } else if (t instanceof Event) {
                String startTime = ((Event) t).getStartTimeOfEvent();
                String endTime = ((Event) t).getEndTimeOfEvent();
                boolean hasStart = false;
                boolean hasEnd = false;
                for (int i = 0; i < timing.length; i++) {
                    if (timing[i].substring(0, 5).equals(startTime)) {
                        hasStart = true;
                        timing[i] = timing[i] + " " + t.getDescription() + " - " + t.getModuleCode() + " |";
                    } else if (timing[i].substring(0, 5).equals(endTime)) {
                        hasEnd = false;
                        hasStart = false;
                        break;
                    } else if (hasStart && !hasEnd) {
                        timing[i] = timing[i] + " " + t.getDescription() + " - " + t.getModuleCode() + " |";
                    }
                }

            } else {
                nonLessonList.add(t);
            }
        }
        for (String i : timing) {
            Ui.print(i);
        }
        Ui.print("\nToday's deadline:");
        Ui.printListGenericType(nonLessonList, "deadline");
    }


    /**
     * Displays tasks on the days within the range.
     * The error message will be printed if startDay and endDay gives wrong range (e.g. endDay < startDay).
     *
     * @param startDate the start of the range.
     * @param endDate   the end of the range.
     */
    public void display(LocalDate startDate, LocalDate endDate) throws InvalidStartEndDateException,
            StartAndEndTimeSameException {
        if (startDate.isAfter(endDate)) {
            throw new InvalidStartEndDateException();
        } else if (startDate.isEqual(endDate)) {
            throw new StartAndEndTimeSameException();
        }
        Ui.print("List of task from "
                + Ui.convertDateToStringWithYear(startDate)
                + " to "
                + Ui.convertDateToStringWithYear(endDate));
        for (LocalDate date = LocalDate.of(2020, 10, 12);
             date.isBefore(LocalDate.of(2021, 6, 1)); date = date.plusDays(1)) {
            if (date.isEqual(startDate)) {
                if (semesterSchedule.get(date).size() != 0) {
                    Ui.print(date.format(DateTimeFormatter.ofPattern("MMM d"))
                            + " :");
                    Ui.printListGenericType(semesterSchedule.get(date), "deadline");

                }
            } else if (date.isAfter(startDate) && date.isBefore(endDate)) {
                if (semesterSchedule.get(date).size() != 0) {
                    Ui.print(date.format(DateTimeFormatter.ofPattern("MMM d"))
                            + " :");
                    Ui.printListGenericType(semesterSchedule.get(date), "deadline");

                }
            } else if (date.isEqual(endDate)) {
                if (semesterSchedule.get(date).size() != 0) {
                    Ui.print(date.format(DateTimeFormatter.ofPattern("MMM d"))
                            + " :");
                    Ui.printListGenericType(semesterSchedule.get(date), "deadline");
                }
            }
        }
    }

    /**
     * Method to display the schedule of 1 date.
     *
     * @param date that user wants to see the schedule of.
     */
    public void displayDate(LocalDate date) throws InvalidDateException {
        String startTime = null;
        String endTime = null;
        boolean taskIsLessonOrEvent = false;
        ArrayList<Task> taskList = semesterSchedule.get(date);

        if (taskList == null) {
            throw new InvalidDateException();
        }
        Ui.print("Here is your schedule on " + date.toString() + "!! :)");

        ArrayList<Deadline> deadlineList = new ArrayList<>();
        String[] timing = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
            "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"
        };
        for (Task t : taskList) {
            if (t instanceof Lesson) {
                startTime = ((Lesson) t).getStartTime();
                endTime = ((Lesson) t).getEndTime();
                boolean hasStart = false;
                boolean hasEnd = false;
                for (int i = 0; i < timing.length; i++) {
                    if (timing[i].substring(0, 5).equals(startTime)) {
                        hasStart = true;
                        timing[i] = timing[i] + " " + t.getDescription() + " - " + t.getModuleCode() + " |";
                    } else if (timing[i].substring(0, 5).equals(endTime)) {
                        hasEnd = false;
                        hasStart = false;
                        break;
                    } else if (hasStart && !hasEnd) {
                        timing[i] = timing[i] + " " + t.getDescription() + " - " + t.getModuleCode() + " |";
                    }
                }
            } else if (t instanceof Event) {
                startTime = ((Event) t).getStartTimeOfEvent();
                endTime = ((Event) t).getEndTimeOfEvent();
                boolean hasStart = false;
                boolean hasEnd = false;
                for (int i = 0; i < timing.length; i++) {
                    if (timing[i].substring(0, 5).equals(startTime)) {
                        hasStart = true;
                        if (!t.getModuleCode().equals("")) {
                            timing[i] = timing[i] + " " + t.getDescription() + " - " + t.getModuleCode() + " at "
                                    + ((Event) t).getAt() + " |";
                        } else {
                            timing[i] = timing[i] + " " + t.getDescription() + " - " + "at "
                                    + ((Event) t).getAt() + " |";
                        }
                    } else if (timing[i].substring(0, 5).equals(endTime)) {
                        hasEnd = false;
                        hasStart = false;
                        break;
                    } else if (hasStart && !hasEnd) {
                        if (!t.getModuleCode().equals("")) {
                            timing[i] = timing[i] + " " + t.getDescription() + " - " + t.getModuleCode() + " at "
                                    + ((Event) t).getAt() + " |";
                        } else {
                            timing[i] = timing[i] + " " + t.getDescription() + " - " + "at "
                                    + ((Event) t).getAt() + " |";
                        }
                    }
                }
            } else {
                deadlineList.add((Deadline) t);
            }
        }
        for (String i : timing) {
            Ui.print(i);
        }
        deadlineList = addToDeadlineList(date, deadlineList);
        //Ui.print("\nDeadlines on " + date.toString() + ":" );
        Ui.print("\nUpcoming Deadlines :");
        Ui.printListGenericType(deadlineList, "deadline");
    }

    /**.
     * Adds deadlines of the next 7 days from current day to the non lesson and event list
     *
     * @param currentDate current date specified.
     * @param list        list of the Deadlines where more deadlines will be added to.
     * @return list of Deadlins
     */
    private ArrayList<Deadline> addToDeadlineList(LocalDate currentDate, ArrayList<Deadline> list) {
        LocalDate oneWeekAfterCurrentDate = currentDate.plusDays(7);
        currentDate = currentDate.plusDays(1);
        for (LocalDate date = currentDate; date.isBefore(oneWeekAfterCurrentDate); date = date.plusDays(1)) {
            for (Task task : this.semesterSchedule.get(date)) {
                if (task instanceof Deadline) {
                    list.add((Deadline) task);
                }
            }
        }
        return list;
    }

    public int getNumberOfTaskInADay(LocalDate date) {
        return semesterSchedule.get(date).size();
    }
}

