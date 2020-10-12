package seedu;
import seedu.task.Deadline;
import seedu.task.Event;
import seedu.task.Lesson;
import seedu.task.Task;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * READ THIS FIRST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * Class for ScheduleManager.
 * Contains the schedule for the user.
 * We will assume that the ScheduleManager is built for AY 2020/2021 Semester 2.
 */
public class ScheduleManager {
	public HashMap<LocalDate, List<Task>> semesterSchedule = new HashMap<>();

	/**
	 * Constructor for ScheduleManager if a ScheduleManager already exist.
	 * @param semesterSchedule
	 */
	public ScheduleManager(HashMap<LocalDate, List<Task>> semesterSchedule) {
		this.semesterSchedule = semesterSchedule;
	}

	/**
	 * Constructor for ScheduleManager if a ScheduleManager has yet to be created.
	 * Main role is to populate the HashMap with the dates in the year as the 'key'
	 * and an empty list of task as the value.
	 */
	public ScheduleManager() {
		this.semesterSchedule = new HashMap<>();
		// Now I will need to populate this hashmap because it is currently empty with no dates.
		for (LocalDate date = LocalDate.of(2021, 1, 1); date.isBefore(LocalDate.of(2021, 6, 1)); date = date.plusDays(1)) {
			this.semesterSchedule.put(date, new ArrayList<>());
		}
	}

	/**
	 * Add lessons.
	 * @param lesson lesson to be added to the schedule manager.
	 */
	public void addLesson(Lesson lesson) {
		DayOfWeek day = lesson.getLessonDayInDayOfWeek();
		for (Map.Entry<LocalDate, List<Task>> entry : this.semesterSchedule.entrySet()) {
			LocalDate key = entry.getKey();
			if (key.getDayOfWeek().equals(day)) {
				this.semesterSchedule.get(key).add(lesson);
			}
		}
	}

	/**
	 * Deadline only got 1 day, so just filter for the
	 * date where I need to add the deadline,
	 * @param deadline add deadline inside the list of tasks of the schedule manager.
	 */
	public void addDeadline(Deadline deadline) {

	}

	/**
	 * Event only got 1 date, so just filter for the
	 * date where I need to add the event.
	 * @param event add event inside the list of tasks of the schedule manager.
	 */
	public void addEvent(Event event) {
		LocalDate date = LocalDate.parse(event.getDateOfEvent());
		this.semesterSchedule.get(date).add(event);
	}

	/**
	 * Displays tasks on the specified day
	 * The error message will be printed if startDay and endDay gives wrong range (e.g. endDay < startDay)
	 * @param day that user wants to see the schedule
	 * FIXME
	 *  - add code and output based on UG
	 *  - handle the task with frequency!
	 */
	public void display(LocalDate day) {
		for (Map.Entry<LocalDate, List<Task>> entry : this.semesterSchedule.entrySet()) {
			LocalDate key = entry.getKey();
			if (key.equals(day)) {
				System.out.println(this.semesterSchedule.get(key).toString());
			}
		}
	}

	/**
	 * Displays tasks on the days within the range.
	 * The error message will be printed if startDay and endDay gives wrong range (e.g. endDay < startDay).
	 * @param startDay the start of the range.
	 * @param endDay the end of the range.
	 * FIXME
	 *  - add code and output based on UG
	 *  - handle the task with frequency!
	 */
	public void display(LocalDate startDay, LocalDate endDay){

	}

	/**
	 * Displays today's tasks.
	 * FIXME
	 *  - add code and output based on UG
	 *  - handle the task with frequency!
	 */
	public void display() {

	}
}
