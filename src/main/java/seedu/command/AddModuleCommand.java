package seedu.command;

import seedu.*;
import seedu.Module;
import seedu.exception.InvalidArgumentsException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a command for adding module.
 */
public class AddModuleCommand extends Command {
    private final Module module;

    /**
     * Creates Module object from arguments given in string.
     * Throws InvalidArgumentsException when the moduleCode is empty or au number is negative.
     * @param strings the argument array in string
     * @throws InvalidArgumentsException if the argument is invalid or cannot be parsed correctly
     */
    public AddModuleCommand(String[] strings) throws InvalidArgumentsException {
        String moduleCode = null;
        String title = null;
        int au_num = 0;
        ArrayList<String> teachingStaffs = new ArrayList<>();

        try {
            for (int i = 0; i < strings.length; i++) {
                String argument = strings[i];
                if (argument == null) {
                    break;
                }
                switch (argument.substring(0, 2)) {
                    case "c/":
                        moduleCode = argument.substring(2);
                        break;
                    case "t/":
                        title = argument.substring(2);
                        break;
                    case "a/":
                        au_num = Integer.parseInt(argument.substring(2));
                        break;
                    case "s/":
                        teachingStaffs.add(argument.substring(2));
                        break;
                }
            }
        } catch (Exception e) {
            throw new InvalidArgumentsException();
        }
        if ((moduleCode == null) || (au_num < 0)) {
            throw  new InvalidArgumentsException();
        }

        this.module = new Module(moduleCode, title, au_num, teachingStaffs);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ScheduleManager scheduleManager, ModuleManager moduleManager, Ui ui) {
        if (moduleManager.checkIfModuleExist(this.module)) {
            Ui.print(this.module + " is already inside the Module Manager!");
        } else {
            moduleManager.addModule(this.module);
            Ui.print("Successfully added to Module Manager! Have fun suffering from " + this.module);
        }
        System.out.println("Here are your modules you are currently taking! " +
                "\n" + Arrays.toString(moduleManager.getListOfModuleCodes().toArray()));
        Ui.showDivider();
    }
}
