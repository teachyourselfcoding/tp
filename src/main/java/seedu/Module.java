package seedu;

import seedu.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Class for Module.
 * <p>
 * moduleCode e.g. ST2132.
 * title e.g. Statistics.
 * aUNumber e.g. 4.
 * teachingStaffs: e.g. ["Dr.Lim(lim@e.nus.sg)",]
 * listOfTasks. e.g.list of tasks in the module.
 * </p>
 */
public class Module {
	private String moduleCode;
	private String title;
	private int aUNumber;
	private ArrayList<String> teachingStaffs = new ArrayList<>();
	private ArrayList<Task> listOfTasks;

	/**
	 * Constructor when I am adding a task that has a module code that has not exist yet.
	 */
	public Module(String moduleCode, String title, int aUNumber, ArrayList<String> teachingStaffs) {
		this.moduleCode = moduleCode;
		this.title = title;
		this.aUNumber = aUNumber;
		this.teachingStaffs = teachingStaffs;
		this.listOfTasks = new ArrayList<>();
	}
	public Module(String moduleCode) {
		this.moduleCode = moduleCode;
		this.listOfTasks = new ArrayList<>();
	}

	public void addTask(Task task){
		listOfTasks.add(task);
	}

	public ArrayList<Task> getListOfTasks(){
		return listOfTasks;

	}

	public String getModuleCode() {
		return this.moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		String information = "\nCourse: " + this.moduleCode + "\n" + "Title: " + this.title + "\n" + "AU: " + this.aUNumber
				+ "\n" + "Teaching Staffs: " + teachingStaffs.toString();
		return information;
	}

	public boolean equals(Module module) {
		return module.getModuleCode().equals(this.moduleCode);
	}

	/**
	 * Deletes the task with the specified description from the list.
	 * @param description
	 */
	public void deleteTask(String description) {
		for (int i = 0; i < this.listOfTasks.size(); i++) {
			Task t = this.listOfTasks.get(i);
			if (t.getDescription().equals(description)) {
				this.listOfTasks.remove(t);
			}
		}
	}

	public void deleteTask(String description, LocalDate date) {
		for (int i = 0; i < this.listOfTasks.size(); i++) {
			Task t = this.listOfTasks.get(i);
			if (t.getDescription().equals(description) && date.isEqual(t.getDate())) {
				this.listOfTasks.remove(t);
			}
		}
	}
}
