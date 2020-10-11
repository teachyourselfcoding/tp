package seedu;

import seedu.task.Task;

import java.util.List;

/**
 * Class for Module Management.
 */
public class Module {
	private String moduleCode;  // e.g. ST2132
	private String title;  // e.g. Statistics
	private int aUNumber;  // e.g. 4
	private String[] teachingStaffs = new String[5];  // e.g. ["Dr.Lim(lim@e.nus.sg)",]
	private List<Task> listOfTasks; // list of tasks in the module. Should we seperate the events and deadlines?

	/**
	 * Constructor for Module Object
	 * @param moduleCode
	 * @param listOfTasks
	 */
	public Module(String moduleCode, List<Task> listOfTasks) {
		this.moduleCode = moduleCode;
		this.listOfTasks = listOfTasks;
	}

	/**
	 * Constructs new Module object.
	 * It will print error message when AU number is not in integer.
	 * @param moduleCode module's code in string
	 * @param title module's title in string
	 * @param aUNumber the AU number for this module
	 * @param teachingStaffs the list of teaching staffs with their contact (seems unnecessary)
	 * @param listOfTasks
	 * FIXME Add error handling
	 */
	public Module(String moduleCode, String title, int aUNumber, String[] teachingStaffs, List<Task> listOfTasks) {
		this.moduleCode = moduleCode;
		this.title = title;
		this.aUNumber = aUNumber;
		this.teachingStaffs = teachingStaffs;
		this.listOfTasks = listOfTasks;
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
}
