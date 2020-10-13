package seedu;

import seedu.task.Task;

import java.util.ArrayList;

/**
 * Class for Module Management.
 * moduleCode e.g. ST2132.
 * title e.g. Statistics.
 * aUNumber e.g. 4.
 * teachingStaffs: e.g. ["Dr.Lim(lim@e.nus.sg)",]
 * listOfTasks. e.g.list of tasks in the module.
 * TODO
 *  - Should we separate the events and deadlines?
 */
public class Module {
	private String moduleCode;  // e.g. ST2132
	private String title;  // e.g. Statistics
	private int aUNumber;  // e.g. 4
	private String[] teachingStaffs = new String[5];  // e.g. ["Dr.Lim(lim@e.nus.sg)",]
	private ArrayList<Task> listOfTasks; // list of tasks in the module. Should we seperate the events and deadlines?



	/**
	 * Constructor when I am adding a task that has a module code that has not exist yet.
	 * Useful as only need the module code to generate this.
	 * User can then update the other properties himself later on.
	 * @param moduleCode moduleCode of the module.
	 */
	public Module(String moduleCode) {
		this.moduleCode = moduleCode;
		this.listOfTasks = new ArrayList<>();
	}

	/**
	 * Constructor for Module Object.
	 * @param moduleCode moduleCode of the module.
	 * @param listOfTasks list of tasks under the module.
	 */
	public Module(String moduleCode, ArrayList<Task> listOfTasks) {
		this.moduleCode = moduleCode;
		this.listOfTasks = listOfTasks;
	}

	/**
	 * Constructs new Module object.
	 * It will print error message when AU number is not in integer.
	 * @param moduleCode module's code in string.
	 * @param title module's title in string.
	 * @param aUNumber the AU number for this module.
	 * @param teachingStaffs the list of teaching staffs with their contact (seems unnecessary).
	 * @param listOfTasks list of tasks under the module.
	 * FIXME Add error handling
	 */
	public Module(String moduleCode, String title, int aUNumber, String[] teachingStaffs, ArrayList<Task> listOfTasks) {
		this.moduleCode = moduleCode;
		this.title = title;
		this.aUNumber = aUNumber;
		this.teachingStaffs = teachingStaffs;
		this.listOfTasks = listOfTasks;
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

	public void setaUNumber(int aUNumber) {
		this.aUNumber = aUNumber;
	}

	public void setTeachingStaffs(String[] teachingStaffs) {
		this.teachingStaffs = teachingStaffs;
	}

	@Override
	public String toString() {
		return this.moduleCode;
	}

	public boolean equals(Module module) {
		return module.getModuleCode() == this.moduleCode;
	}
}
