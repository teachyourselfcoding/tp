package seedu;

import seedu.module.Module;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the Class for functions related to the CLI User interface
 * and used to print out exception messages for troubleshooting purpose.
 */
public class Ui {
    private Scanner scanner;
    private static String SEPARATOR = "<-------------------------------------------------------------->";

    /**
     * Constructor of Ui.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Print out the on startup message.
     */
    public void welcomeMessage() {
        String welcomeMessage =
                "█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█\n" +
                "█░░╦─╦╔╗╦─╔╗╔╗╔╦╗╔╗░░█\n" +
                "█░░║║║╠─║─║─║║║║║╠─░░█\n" +
                "█░░╚╩╝╚╝╚╝╚╝╚╝╩─╩╚╝░░█\n" +
                "█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█" +
                "_____________________________________________\n"
                + "Whats up people! DueQuest is here to save the day\n"
                + "DueQuest for students by students\n"
                + "So, what can I do for you?\n";
        System.out.print(welcomeMessage);
        System.out.println(SEPARATOR);
    }

    /**
     * Print out the exit message.
     */
    public void byeMessage() {
        String byeMessage =

                "┌───── •✧✧• ─────┐\n" +
                "     -GOODBYE-    \n" +
                "└───── •✧✧• ─────┘\n"+
                "Aye captain. This is DueQuest Signing out!\n";
        System.out.print(byeMessage);

    }

    /**
     * Prints out message for the help guide when a HelpCommand is execute.
     */
    public void printHelpGuide() {
        String helpMessage = " This is the list of Command Available, please refer to the User Guide\n"
                +  " display - display today's Schedule, module info, list of Task \n"
                +  " module  - add a module\n"
                +  " add     - add a deadline, event, lesson\n"
                +  " edit    - edit information\n"
                +  " delete  - delete task in a module\n"
                +  " bye    - exit duequest";
        print(helpMessage);
        System.out.println(SEPARATOR);
    }

    /**
     * Scan and Read in user input on the CLI.
     *
     * @return String command.
     */
    public String readCommand() {
        System.out.println("Please type the next command!");
        System.out.println(SEPARATOR);
        String commandString;
        if (scanner.hasNextLine()) {
            commandString = scanner.nextLine();
        } else {
            commandString = "";
        }

        return commandString;
    }

    /**.
     * use the scanner to check if there is an input
     *
     * @return boolean
     */
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public void printClashesMessage() {
        System.out.println("(╯°□°）╯︵ ┻━┻");
        System.out.println(SEPARATOR);
        System.out.println("Clashes detected, task is not added.");
        System.out.println(SEPARATOR);
    }

    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    /**
     * Prints the Exception information based on the parameters.
     *
     * @param e DukeExceptionType.
     */
    public void showError(DueQuestExceptionType e) {
        switch (e) {
        case MISSING_DEADLINE:
            System.out.println("(╯°□°）╯︵ ┻━┻");
            System.out.println("Missing Deadline!");
            System.out.println(SEPARATOR);
            break;
        case MISSING_EVENT_INFO:
            System.out.println("(╯°□°）╯︵ ┻━┻");
            System.out.println("Missing Event Information!");
            System.out.println(SEPARATOR);
            break;
        case WRONG_INPUT_FORMAT:
            System.out.println("(╯°□°）╯︵ ┻━┻");
            System.out.println("Wrong Input format");
            System.out.println(SEPARATOR);
            break;
        case MISSING_DESCRIPTION:
            System.out.println("(╯°□°）╯︵ ┻━┻");
            System.out.println("Missing Description!");
            System.out.println(SEPARATOR);
            break;
        case WRONG_DATE_FORMAT:
            System.out.println("(╯°□°）╯︵ ┻━┻");
            System.out.println("Missing Or Wrong Date Format! Enter in DD-MM-YYYY:");
            System.out.println(SEPARATOR);
            break;
        case INVALID_COMMAND:
            System.out.println("(╯°□°）╯︵ ┻━┻");
            System.out.println("No proper Commands Detected, retype again!");
            System.out.println(SEPARATOR);
            break;
        default:
        }
    }

    public static void print(String text) {
        System.out.println(text);
    }

    /**
     * Used to print any generic list.
     *
     * @param lists the provided list of generic type.
     * @param <T>   the object type
     */
    public static <T> void printListGenericType(ArrayList<T> lists, String modifier) {
        if (lists != null) {
            for (T i : lists) {
                print(i.toString());
            }
        }
        if (lists.size() == 0) {
            System.out.println("(╯°□°）╯︵ ┻━┻");
            System.out.println("You don't have any " + modifier + "!");
        }
        print("");
    }

    /**
     * Convert LocalDate object into readable string format without year.
     * E.g 12 JUN.
     * @param date Local date object to convert
     * @return String
     */
    public static String convertDateToString(LocalDate date) {
        String stringDate = date.format(DateTimeFormatter.ofPattern("d MMM"));
        return stringDate;
    }

    /**
     * Convert LocalDate object into readable string format with year included.
     * E.g 12 JUN 20.
     * @param date date to be converted.
     * @return string form of the converted LocalDate object with year.
     */
    public static String convertDateToStringWithYear(LocalDate date) {
        String stringDate = date.format(DateTimeFormatter.ofPattern("d MMM YY"));
        return stringDate;
    }

    public static void printInvalidArgumentsErrorMessage() {
        System.out.println("(╯°□°）╯︵ ┻━┻");
        System.out.println(SEPARATOR);
        System.out.println("Sorry, please check your arguments again.");
        System.out.println(SEPARATOR);
    }

    public static void printModuleAlreadyExistMessage() {
        System.out.println("(╯°□°）╯︵ ┻━┻");
        System.out.println(SEPARATOR);
        System.out.println("The module with the same code already exists.");
        System.out.println(SEPARATOR);
    }

    public static void printModuleNotExistMessage() {
        System.out.println("(╯°□°）╯︵ ┻━┻");
        System.out.println(SEPARATOR);
        System.out.printf("The module does not exists.\n");
        System.out.println(SEPARATOR);
    }

    public static void printMissingLessonTimingMessage() {
        System.out.println("(╯°□°）╯︵ ┻━┻");
        System.out.println(SEPARATOR);
        System.out.println("The lesson's time information is missing.");
        System.out.println(SEPARATOR);
    }

    public static void printMissingDeadlineTimingDetailsMessage() {
        System.out.println("(╯°□°）╯︵ ┻━┻");
        System.out.println(SEPARATOR);
        System.out.println("The deadline's time information is missing.");
        System.out.println(SEPARATOR);
    }

    public static void printMissingEventDateAndTimeDetailsMessage() {
        System.out.println("(╯°□°）╯︵ ┻━┻");
        System.out.println(SEPARATOR);
        System.out.println("The event's date, time or location information is missing.");
        System.out.println(SEPARATOR);
    }

    public static void printEmptyArgumentMessage() {
        System.out.println("(╯°□°）╯︵ ┻━┻");
        System.out.println(SEPARATOR);
        System.out.println("The arguments for this command cannot be empty.");
        System.out.println(SEPARATOR);
    }

    public static void printWrongTimeFormatMessage() {
        System.out.println("(╯°□°）╯︵ ┻━┻");
        System.out.println(SEPARATOR);
        System.out.println("The format for time should be in HH:MM format.");
        System.out.println("Make sure the time you input is valid. E.g. not 30:00, 21:99");
        System.out.println("Make sure MM is 00. E.g. 18:00 is allowed but not 18:01");
        System.out.println(SEPARATOR);
    }

    public static void printInvalidModuleCode() {
        System.out.println("(╯°□°）╯︵ ┻━┻");
        System.out.println(SEPARATOR);
        System.out.println("The module code is not valid or not typed in.");
        System.out.println("Please make sure your module code is typed in follows the specifications stated "
                + "in the user guide!");
        System.out.println(SEPARATOR);
    }

    public static void printInvalidDateFormatMessage() {
        System.out.println("(╯°□°）╯︵ ┻━┻");
        System.out.println(SEPARATOR);
        System.out.println("Please give the date in correct format and a valid date."
                + " It should be in YYYY-MM-DD format.");
        System.out.println(SEPARATOR);
    }

    public static void printInvalidStartEndDate() {
        System.out.println("(╯°□°）╯︵ ┻━┻");
        System.out.println(SEPARATOR);
        System.out.println("The Start date cannot be after End date");
    }

    public static void printInvalidDateMessage() {
        System.out.println("(╯°□°）╯︵ ┻━┻");
        System.out.println(SEPARATOR);
        System.out.println("Please give the date between 2021/01/01 and 2021/05/31.");
        System.out.println(SEPARATOR);
    }

    public static void printInvalidFrequencyMessage() {
        System.out.println("(╯°□°）╯︵ ┻━┻");
        System.out.println(SEPARATOR);
        System.out.println("Frequency should be a digit from 1 to 7");
        System.out.println("E.g. 1 represents Monday, 3 represents Wednesday");
        System.out.println(SEPARATOR);
    }

    public static void printStartAndEndTimeCannotBeTheSameMessage() {
        System.out.println("ლ(ಠ益ಠლ)");
        System.out.println(SEPARATOR);
        System.out.println("Start and End time cannot be the same!");
        System.out.println(SEPARATOR);
    }

    public static void printMissingDeadlineDescriptionMessage() {
        System.out.println("ლ(ಠ益ಠლ)");
        System.out.println(SEPARATOR);
        System.out.println("Your deadline is missing the description!");
        System.out.println(SEPARATOR);
    }

    public static void printMissingLessonDescriptionMessage() {
        System.out.println("ლ(ಠ益ಠლ)");
        System.out.println(SEPARATOR);
        System.out.println("Your lesson is missing the description!");
        System.out.println(SEPARATOR);
    }

    public static void printMissingEventDescriptionMessage() {
        System.out.println("ლ(ಠ益ಠლ)");
        System.out.println(SEPARATOR);
        System.out.println("Your event is missing the description!");
        System.out.println(SEPARATOR);
    }

    public static void printInvalidInputMessage() {
        System.out.println("ლ(ಠ益ಠლ)");
        System.out.println(SEPARATOR);
        System.out.println("Input doesnt seems right, please check again");
        System.out.println(SEPARATOR);
    }

    public static void printMissingDeleteDetails() {
        System.out.println("ლ(ಠ益ಠლ)");
        System.out.println(SEPARATOR);
        System.out.println("Details cannot be missing");
        System.out.println(SEPARATOR);
    }

    public static void printSuccessfulEdit() {
        System.out.println("ლ(ಠ益ಠლ)");
        System.out.println(SEPARATOR);
        System.out.println("Done. The edit has been made.");
        System.out.println(SEPARATOR);
    }

    public static void printStartTimeCannotBeAfterEndTimeMessage() {
        System.out.println("ლ(ಠ益ಠლ)");
        System.out.println(SEPARATOR);
        System.out.println("The start time cannot be after the end time!");
        System.out.println(SEPARATOR);
    }

    public static void printStartTimeAndEndTimeCannotBeBeforeEightOClockMessage() {
        System.out.println("ლ(ಠ益ಠლ)");
        System.out.println(SEPARATOR);
        System.out.println("The start time and end time cannot be before 8am!");
        System.out.println(SEPARATOR);
    }


    public static void printModuleDoesNotExistMessage() {
        System.out.println("(ノಠ益ಠ)ノ彡┻━┻");
        System.out.println(SEPARATOR);
        System.out.println("Module does not exist, please add the module first!");
        System.out.println(SEPARATOR);
    }

    public static void printMissingModuleCodeOrInvalidModuleCodeMessage() {
        System.out.println("(ノಠ益ಠ)ノ彡┻━┻");
        System.out.println(SEPARATOR);
        System.out.println("Your module code is either missing or in the wrong format!");
        System.out.println("Please make sure your module code is typed in follows the specifications stated "
                + "in the user guide!");
        System.out.println(SEPARATOR);
    }

    public static void printInvalidScoreErrorMessage() {
        System.out.println("(ノಠ益ಠ)ノ彡┻━┻");
        System.out.println(SEPARATOR);
        System.out.println("Please give the invalid score.");
        System.out.println(SEPARATOR);
    }

    public static void printNoSuchAssessment(String title, String moduleCode) {
        System.out.println("(ノಠ益ಠ)ノ彡┻━┻");
        System.out.println(SEPARATOR);
        System.out.printf("There is no assessment with the title %s in Module %s \n", title, moduleCode);
        System.out.println(SEPARATOR);
    }

    public static void printInvalidEditTypeMessage() {
        System.out.println("(ノಠ益ಠ)ノ彡┻━┻");
        System.out.println(SEPARATOR);
        System.out.printf("Looks like an invalid type edit to me");
        System.out.println(SEPARATOR);
    }

    public static void printModuleInformation(Module m){
        System.out.println(m);
    }

    public static void printModuleCode(Module m){
        System.out.println(m.getModuleCode());
    }

    public static void printModulePropertyEditedMessage(){
        System.out.println("(◕‿◕✿)");
        System.out.println(SEPARATOR);
        System.out.println("Module tasks property has been updated");
        System.out.println(SEPARATOR);
    }

    public static void printModuleFrequencyEditedMessage(){
        System.out.println("(◕‿◕✿)");
        System.out.println(SEPARATOR);
        System.out.println("Module tasks frequency has been updated");
        System.out.println(SEPARATOR);
    }
    public static void printTaskFrequencyEditedMessage(){
        System.out.println("(◕‿◕✿)");
        System.out.println(SEPARATOR);
        System.out.println("Task's frequency has been updated");
        System.out.println(SEPARATOR);
    }

    public static void printTaskFrequencyNotEditedMessage(){
        System.out.println("(ง'̀-'́)ง");
        System.out.println(SEPARATOR);
        System.out.println("Task not found");
        System.out.println(SEPARATOR);
    }

    public static void printModuleDateEditedMessage(){
        System.out.println("(◕‿◕✿)");
        System.out.println(SEPARATOR);
        System.out.println("The tasks' date in this module has been updated");
        System.out.println(SEPARATOR);
    }

    public static void printModuleDateNotEditedMessage(){
        System.out.println("(ง'̀-'́)ง");
        System.out.println(SEPARATOR);
        System.out.println("Cant find a matching task");
        System.out.println(SEPARATOR);
    }

    public static void printTaskDateEditedMessage(){
        System.out.println("(◕‿◕✿)");
        System.out.println(SEPARATOR);
        System.out.println("The tasks' date has been updated");
        System.out.println(SEPARATOR);
    }

    public static void printTaskDateNotEditedMessage(){
        System.out.println("(ง'̀-'́)ง");
        System.out.println(SEPARATOR);
        System.out.println("Cant find a matching task");
        System.out.println(SEPARATOR);
    }


    public static void printModuleDeletedMessage(){
        System.out.println("(◕‿◕✿)");
        System.out.println(SEPARATOR);
        System.out.println("This module has been deleted");
        System.out.println(SEPARATOR);
    }

    public static void printTaskDeletedMessage(){
        System.out.println("(◕‿◕✿)");
        System.out.println(SEPARATOR);
        System.out.println("1 or more tasks has been deleted");
        System.out.println(SEPARATOR);
    }

    public static void printTaskModuleCodeDeletedMessage(){
        System.out.println("(◕‿◕✿)");
        System.out.println(SEPARATOR);
        System.out.println("All tasks matching module code have been deleted");
        System.out.println(SEPARATOR);
    }

    public static void printTaskModuleCodeNotDeletedMessage(){
        System.out.println("(ง'̀-'́)ง");
        System.out.println(SEPARATOR);
        System.out.println("All tasks matching module code have been deleted");
        System.out.println(SEPARATOR);
    }

    public static void printTaskNotDeletedMessage(){
        System.out.println("(ง'̀-'́)ง");
        System.out.println(SEPARATOR);
        System.out.println("No task matching description or module code or date found");
        System.out.println(SEPARATOR);
    }


    public static void printModuleTaskEditedMessage(){
        System.out.println("(◕‿◕✿)");
        System.out.println(SEPARATOR);
        System.out.println("Module task's date has been edited");
        System.out.println(SEPARATOR);
    }

    public static void printTaskNotEditedMessage(){
        System.out.println("(ง'̀-'́)ง");
        System.out.println(SEPARATOR);
        System.out.println("Task not found");
        System.out.println(SEPARATOR);
    }

    public static void printLessonAddedMessage(){
        System.out.println("(◕‿◕✿)");
        System.out.println(SEPARATOR);
        System.out.println("Got it, added lesson to the Schedule Manager and Module Manager!");
        System.out.println(SEPARATOR);
    }

    public static void printEventAddedMessage(){
        System.out.println("(◕‿◕✿)");
        System.out.println(SEPARATOR);
        System.out.println("Event added to both Schedule manager and Module manager");
        System.out.println(SEPARATOR);
    }

    public static void printEventAddedtoScheduleOnlyMessage(){
        System.out.println("(◕‿◕✿)");
        System.out.println(SEPARATOR);
        System.out.println("Event added to Schedule Manager only");
        System.out.println(SEPARATOR);
    }

    public static void printAddtionalTxtCreationError(){
        System.out.println("(ง'̀-'́)ง");
        System.out.println(SEPARATOR);
        System.out.println("Error: additional.txt file cannot be created.");
        System.out.println(SEPARATOR);
    }

    public static void printAddtionalTxtWritingError(){
        System.out.println("(ง'̀-'́)ง");
        System.out.println(SEPARATOR);
        System.out.println("Error: additional.txt file cannot be written.");
        System.out.println(SEPARATOR);
    }

    public static void printFileLoadingMessage(){
        System.out.println("(◕‿◕✿)");
        System.out.println(SEPARATOR);
        System.out.println("Loading data from the file...");
        System.out.println(SEPARATOR);
    }

    public static void printFileLoadedMessage(){
        System.out.println("(☞ﾟ∀ﾟ)☞");
        System.out.println(SEPARATOR);
        System.out.println("Loading completed.");
        System.out.println(SEPARATOR);
    }

    public static void printFileMissingError(){
        System.out.println("(ง'̀-'́)ง");
        System.out.println(SEPARATOR);
        System.out.println("Error: the file is not found.");
        System.out.println(SEPARATOR);
    }

    public static void printFileWritingError(){
        System.out.println("(ง'̀-'́)ง");
        System.out.println(SEPARATOR);
        System.out.println("Error: the file cannot be written.");
        System.out.println(SEPARATOR);
    }

    public static void printModuleMissingWritingError(){
        System.out.println("(ง'̀-'́)ง");
        System.out.println(SEPARATOR);
        System.out.println("Cannot find the relevant module");
        System.out.println(SEPARATOR);
    }












}

