package seedu;

import java.util.Scanner;

/**
 *Represents the Class for functions related to the CLI User interface
 * and used to print out exception messages for troubleshooting purpose.
 */
public class Ui {
    private Scanner in;
    public Ui() { in = new Scanner(System.in);
    }

    /**
     *print out the on startup message
     */
    public void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = logo +
                "_____________________________________________\n"
                +"Whats up people! Duke is here to save the day\n"
                +"Soooo, what can I do for you?\n";
        System.out.print(welcomeMessage);
        showDivider();
    }

    /**
     * print out the exit message
     */
    public void byeMessage() {
        String byeMessage =
                "Aye captain. This is Duke Signing out!\n";
        System.out.print(byeMessage);
        Ui.showDivider();
    }

    /**
     *print out a divider line for better readability of CLI
     */
    public static void showDivider(){
        System.out.println("_____________________________________________\n");
    }

    /**
     * Scan and Read in user input on the CLI
     *
     * @return String command
     */
    public String readCommand() {
        String command=in.nextLine();
        return command;
    }

    /**
     * Prints the Exception information based on the parameters
     *
     * @param e DukeExceptionType
     */
    public void showError(DueQuestExceptionType e) {
        switch (e) {
        case MISSING_DEADLINE:
            System.out.println("Missing Deadline!");
            showDivider();
            break;
        case MISSING_EVENT_INFO:
            System.out.println("Missing Event Information!");
            showDivider();
            break;
        case WRONG_INPUT_FORMAT:
            System.out.println("Wrong Input Informat");
            showDivider();
            break;
        case MISSING_DESCRIPTION:
            System.out.println("Missing Description!");
            showDivider();
            break;
        case INVALID_COMMAND:
            System.out.println("No proper Commands Detected, retype again!");
            showDivider();
            break;
        default:
        }
    }


}
