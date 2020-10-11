package seedu;
import seedu.task.Task;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class for ScheduleManager.
 * Contains the schedule for the user.
 * We will assume that the ScheduleManager is built for AY 2020/2021 Semester 2
 */
public class ScheduleManager {
	public HashMap<LocalDate, List<Task>> semesterSchedule = new HashMap<>();

	/**
	 * Constructor for ScheduleManager if a ScheduleManager has yet to be created
	 */
	public ScheduleManager() {
		this.semesterSchedule = new HashMap<>();
		// Now I will need to populate this hashmap because it is currently empty with no dates.
		for (LocalDate date = LocalDate.of(2021, 1, 1); date.isBefore(LocalDate.of(2021, 6, 1)); date.plusDays(1)) {
			this.semesterSchedule.put(date, new ArrayList<>());
		}
	}

	/**
	 * Constructor for ScheduleManager if a ScheduleManager already exist
	 * @param semesterSchedule
	 */
	public ScheduleManager(HashMap<LocalDate, List<Task>> semesterSchedule) {
		this.semesterSchedule = semesterSchedule;
	}
	/**
	 * Displays tasks on the days within the range.
	 * The error message will be printed if startDay and endDay gives wrong range (e.g. endDay < startDay)
	 * @param startDay the start of the range
	 * @param endDay the end of the range
	 * FIXME
	 *  - add code and output based on UG
	 *  - handle the task with frequency!
	 */
	void display(LocalDate startDay, LocalDate endDay){

	}

	/**
	 * Displays today's tasks.
	 * FIXME
	 *  - add code and output based on UG
	 *  - handle the task with frequency!
	 */
	void display() {

	}
}
