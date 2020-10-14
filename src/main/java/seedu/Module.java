package seedu;

import com.sun.source.tree.ArrayAccessTree;
import seedu.task.Task;

import javax.print.DocFlavor;
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
		String information = "Course: " + this.moduleCode + "\n" + "Title: " + this.title + "\n" +
				"Teaching Staffs: " + teachingStaffs.toString();
		return information;
	}

	public boolean equals(Module module) {
		return module.getModuleCode().equals(this.moduleCode);
	}
}
