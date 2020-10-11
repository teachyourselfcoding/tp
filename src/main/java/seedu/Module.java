package seedu;

/**
 * Class for Module Management.
 */
public class Module {
	String moduleCode;  // e.g. ST2132
	String title;  // e.g. Statistics
	int aUNumber;  // e.g. 4
	String[] teachingStaffs = new String[5];  // e.g. ["Dr.Lim(lim@e.nus.sg)",]

	/**
	 * Constructs new Course object.
	 * It will print error message when AU number is not in integer.
	 * @param moduleCode module's code in string
	 * @param title module's title in string
	 * @param aUNumber the AU number for this module
	 * @param teachingStaffs the list of teaching staffs with their contact (seems unnecessary)
	 * FIXME Add error handling
	 */
	public Module(String moduleCode, String title, int aUNumber, String[] teachingStaffs) {
		this.moduleCode = moduleCode;
		this.title = title;
		this.aUNumber = aUNumber;
		this.teachingStaffs = teachingStaffs;
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
