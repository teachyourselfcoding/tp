package seedu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *Represents the Class for functions related to the CLI User interface
 * and used to print out exception messages for troubleshooting purpose.
 */
public class Ui {
    private Scanner scanner;
    private static String SEPARATOR="===================";

    /**
     * Constructor of Ui.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     *print out the on startup message.
     */
    public void welcomeMessage() {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        logo +
        String welcomeMessage =
                "_____________________________________________\n"
                +"Whats up people! Duke is here to save the day\n"
                +"So, what can I do for you?\n";
        System.out.print(welcomeMessage);
        System.out.println(SEPARATOR);
    }

    /**
     * print out the exit message.
     */
    public void byeMessage() {
        String byeMessage =
                "Aye captain. This is Duke Signing out!\n";
        System.out.print(byeMessage);
        
    }
    
    /**
     * Scan and Read in user input on the CLI.
     * @return String command.
     */
    public String readCommand() {
        System.out.println(SEPARATOR);
        System.out.println("Please type the command!");
        System.out.println(SEPARATOR);

        String commandString = scanner.nextLine();
        return commandString;
    }

    public String readYesOrNo() {
        System.out.println(SEPARATOR);
        System.out.println("Clashes detected, are you sure you want to add the task? Answer Yes or No");
        System.out.println(SEPARATOR);

        String input = scanner.nextLine();
        return input;
    }

    /**
     * Prints the Exception information based on the parameters.
     * @param e DukeExceptionType
     */
    public void showError(DueQuestExceptionType e) {
        switch (e) {
        case MISSING_DEADLINE:
            System.out.println("Missing Deadline!");
            System.out.println(SEPARATOR);
            break;
        case MISSING_EVENT_INFO:
            System.out.println("Missing Event Information!");
            System.out.println(SEPARATOR);
            break;
        case WRONG_INPUT_FORMAT:
            System.out.println("Wrong Input format");
            System.out.println(SEPARATOR);
            break;
        case MISSING_DESCRIPTION:
            System.out.println("Missing Description!");
            System.out.println(SEPARATOR);
            break;
        case WRONG_DATE_FORMAT:
            System.out.println("Missing Or Wrong Date Format! Enter in DD-MM-YYYY:");
            System.out.println(SEPARATOR);
            break;
        case INVALID_COMMAND:
            System.out.println("No proper Commands Detected, retype again!");
            System.out.println(SEPARATOR);
            break;
        default:
        }
    }

    public static void print(String text){
        System.out.println(text);
    }

    public static <T> void printListGenericType(ArrayList<T> lists) {
        if (lists != null) {
            for (T i: lists) {
                print(i.toString());
            }
        }
        if (lists.size() == 0) {
            System.out.println("You don't have any Deadlines!");
        }
        print("");
    }

    /**
     * convert LocalDate object into readable string format without year
     * E.g 12 JUN
     * @param date
     * @return
     */
    public static String convertDateToString(LocalDate date){
        String stringDate = date.format(DateTimeFormatter.ofPattern("d MMM"));
        return stringDate;
    }

    /**
     * convert LocalDate object into readable string format with year included
     * E.g 12 JUN 20
     * @param date
     * @return
     */

    public static String convertDateToStringWithYear(LocalDate date){
        String stringDate = date.format(DateTimeFormatter.ofPattern("d MMM YY"));
        return stringDate;
    }

    public static void printInvalidArgumentsErrorMessage() {
        System.out.println(SEPARATOR);
        System.out.println("Sorry, please check your arguments again.");
        System.out.println(SEPARATOR);
    }

    public static void printModuleAlreadyExistMessage() {
        System.out.println(SEPARATOR);
        System.out.println("The module with the same code already exists.");
        System.out.println(SEPARATOR);
    }

    public static void printModuleNotExistMessage() {
        System.out.println(SEPARATOR);
        System.out.printf("The module does not exists.\n");
        System.out.println(SEPARATOR);
    }

    public static void printMissingLessonTimingMessage() {
        System.out.println(SEPARATOR);
        System.out.println("The lesson's time information is missing.");
        System.out.println(SEPARATOR);
    }

    public static void printMissingDeadlineTimingDetailsMessage() {
        System.out.println(SEPARATOR);
        System.out.println("The deadline's time information is missing.");
        System.out.println(SEPARATOR);
    }

    public static void printEmptyArgumentMessage() {
        System.out.println(SEPARATOR);
        System.out.println("The arguments for this command cannot be empty.");
        System.out.println(SEPARATOR);
    }

    public static void printInvalidModuleCode() {
        System.out.println(SEPARATOR);
        System.out.println("The module code is not valid or not typed in.");
        System.out.println(SEPARATOR);
    }

    public static void printInvalidDateFormatMessage() {
        System.out.println(SEPARATOR);
        System.out.println("Please give the date in correct format.");
        System.out.println(SEPARATOR);
    }

    public static void printInvalidDateMessage() {
        System.out.println(SEPARATOR);
        System.out.println("Please give the date between 2020/10/21 and 2021/05/31.");
        System.out.println(SEPARATOR);
    }

    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }
}
