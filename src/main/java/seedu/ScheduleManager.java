package seedu;
import seedu.task.Deadline;
import seedu.task.Event;
import seedu.task.Lesson;
import seedu.task.Task;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	public static HashMap<LocalDate, ArrayList<Task>> semesterSchedule = new HashMap<>();

	/**
	 * Constructor for ScheduleManager if a ScheduleManager already exist.
	 * @param semesterSchedule
	 */
	public ScheduleManager(HashMap<LocalDate, ArrayList<Task>> semesterSchedule) {
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
		for (LocalDate date = LocalDate.of(2020, 10, 12); date.isBefore(LocalDate.of(2021, 6, 1)); date = date.plusDays(1)) {
			this.semesterSchedule.put(date, new ArrayList<>());
		}
	}


	/**
	 * Add lessons.
	 * @param lesson lesson to be added to the schedule manager.
	 */
	public void addLesson(Lesson lesson) {
		DayOfWeek day = lesson.getLessonDayInDayOfWeek();
		for (Map.Entry<LocalDate, ArrayList<Task>> entry : this.semesterSchedule.entrySet()) {
			LocalDate key = entry.getKey();
			if (key.getDayOfWeek().equals(day)) {
				this.semesterSchedule.get(key).add(lesson);
			}
		}
	}
	/**
	 * Add lessons on specific days
	 * @param lesson lesson to be added to the schedule manager.
	 */
	public void addLessonOnSpecificDays(Lesson lesson) {
		semesterSchedule.get(lesson.getDate()).add(lesson);
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

	public void displayTodaySchedule(){
		LocalDate todayDate = LocalDate.now();
		Ui.print(todayDate.toString() + " Schedule:");
		ArrayList<Task> taskList = semesterSchedule.get(todayDate);
		String[] timing = {"08:00","09:00","10:00","11:00", "12:00", "13:00", "14:00", "15:00","16:00","17:00","18:00","19:00"
				,"20:00","21:00","22:00","23:00"};
		for (Task t: taskList) {
			if (t instanceof Lesson){
				String startTime = ((Lesson) t).getStartTime();
				String endTime = ((Lesson) t).getEndTime();
				boolean hasStart=false;
				boolean hasEnd = false;
				for(int i = 0; i< timing.length;i++){
					if (timing[i].equals(startTime) ){
						hasStart = true;
						timing[i]= timing[i]+ " " + t.getDescription() + ", " + t.getModuleCode();
					} else if(timing[i].equals(endTime)){
						hasEnd = false;
						hasStart = false;
						break;
					}else if(hasStart && !hasEnd) {
						timing[i] = timing[i] + " " + t.getDescription() + ", " + t.getModuleCode();
					}

				}

			}
		}
		for (String i: timing){
			Ui.print(i);
		}
	}
	/**
	 * Displays tasks on the specific days.
	 * @param specificDate the specific day
	 * FIXME
	 *  - add code and output based on UG
	 *  - handle the task with frequency!
	 */
	public void display(LocalDate specificDate){
		ArrayList<Task> list =  semesterSchedule.get(specificDate);
		if (list!=null){
			Ui.print("List of task on " + specificDate.toString() + " :");
			Ui.printListGenericType(list);
			Ui.showDivider();
		} else {
			Ui.print("No Task on that " + specificDate.toString());
		}
	}

	/*
	 * Displays tasks on the days within the range.
	 * The error message will be printed if startDay and endDay gives wrong range (e.g. endDay < startDay).
	 * @param startDate the start of the range.
	 * @param endDate the end of the range.
>>>>>>> upstream/master
	 * FIXME
	 *  - add code and output based on UG
	 *  - handle the task with frequency!
	 */

	public void deleteTask(String description, LocalDate date){
		if(semesterSchedule.get(date).size() != 0){
			semesterSchedule.get(date).removeIf(task -> task.getDescription().equals(description));
		}
		else{
			System.out.println("No task on this date");
		}
	}

	public void deleteTask(String description){
		for (LocalDate date = LocalDate.of(2020, 10, 12); date.isBefore(LocalDate.of(2021, 6, 1)); date = date.plusDays(1)) {
			if(semesterSchedule.get(date).size() != 0){
				semesterSchedule.get(date).removeIf(task -> task.getDescription().equals(description));
			}
		}
	}
	public void display(LocalDate startDate, LocalDate endDate){
		Ui.print("List of task from " + startDate.toString() + " to " + endDate.toString());
		for (LocalDate date = LocalDate.of(2020, 10, 12); date.isBefore(LocalDate.of(2021, 6, 1)); date = date.plusDays(1)) {
			if (date.isAfter(startDate) && date.isBefore(endDate)){
				if(semesterSchedule.get(date).size() != 0){
					Ui.print(date.format(DateTimeFormatter.ofPattern("MMM d"))
							+ " schedule :");
					Ui.printListGenericType(semesterSchedule.get(date));

				}
			}
		}
	}

	/**
	 * Displays today's tasks.
	 * FIXME
	 *  - add code and output based on UG
	 *  - handle the task with frequency!
	 */
	public void display() {

	}

	/**
	 * update the schedule upon adding new task through ModuleManager
	 */
	public static void updateSchedule(LocalDate date, Task task){
		semesterSchedule.get(date).add(task);
	}
}
