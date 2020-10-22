package seedu;

import seedu.command.AddCommand;
import seedu.command.AddModuleCommand;
import seedu.command.Command;
import seedu.command.DeleteCommand;
import seedu.command.DisplayCommand;
import seedu.command.DoneCommand;
import seedu.command.ExitCommand;
import seedu.command.FindCommand;
import seedu.command.ListCommand;
import seedu.command.EditTaskCommand;
import seedu.exception.*;
import seedu.task.Deadline;
import seedu.task.Event;
import seedu.task.Lesson;
import seedu.task.ToDo;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;


/**
 *Parser Object is used for translating String user input into.
 * a actionable Command object for execution.
 */
public class Parser {
    public Parser() {
    }

    /**
     * Convert the given string input into a subclass of Command class.
     * return different subclass of Command class.
     * @param input User input.
     * @return a subclass of Command class.
     * @throws DueQuestException if invalid input.
     */
    public static Command parse(String input) throws DueQuestException {
        int taskNum;

        try {
            String[] words = input.split(" ");

            switch (words[0].toLowerCase()) {
                case "bye":
                    //Fallthrough
                    return new ExitCommand();
                case "list":
                    //Fallthrough
                    return new ListCommand();
                case "done":
                    taskNum = Integer.parseInt(words[1]);
                    //Fallthrough
                    return new DoneCommand(taskNum - 1);
                case "delete":
                    //Fallthrough
                    return validateDeleteCommand(input);
                case "find":
                    String[] sentence = input.toLowerCase().split(" ", 2);
                    String keywords = sentence[1];
                    //Fallthrough
                    return new FindCommand(keywords);
                case "todo":
                    ToDo todo = validateToDo(input);
                    //Fallthrough
                    return new AddCommand(todo);
                case "deadline":
                    Deadline deadline = validateDeadline(input);
                    //Fallthrough
                    return new AddCommand(deadline);
                case "event":
                    Event ev = validateEvent(input);
                    //Fallthrough
                    return new AddCommand(ev);
                case "display":
                    return parseDisplayCommand(input);
                case "lesson":
                    Lesson lesson = parseLesson(input);
                    return new AddCommand(lesson);
                case "module": // adding a module
                    return new AddModuleCommand(Arrays.copyOfRange(words, 1, input.length()));  // only pass the arguments
                case "edit":
                    return validateEditTaskCommand(input);
                default:
                    throw new DueQuestException(DueQuestExceptionType.INVALID_COMMAND);
            }
        } catch (InvalidArgumentsException e) {
            Ui.printInvalidArgumentsErrorMessage();
        } catch (MissingLessonTimingException e) {
            Ui.printMissingLessonTimingMessage();
        } catch (EmptyArgumentException e) {
            Ui.printEmptyArgumentMessage();
        } catch (InvalidModuleCodeException e) {
            Ui.printInvalidModuleCode();
        } catch (WrongDateFormatException e) {
            Ui.printInvalidDateFormatMessage();
        } catch (InvalidDateException e) {
            Ui.printInvalidDateMessage();
        } catch (MissingDeadlineTimingDetailsException e) {
            Ui.printMissingDeadlineTimingDetailsMessage();
        }
        return null;  // the function must return something
    }

    /**
     * Used to validate and check for any errors in the user input.
     * for ToDo object.
     * @param  input representing user input.
     * @return Todo object.
     * @throws DueQuestException if missing information.
     */
    public static ToDo validateToDo(String input) throws DueQuestException {
        ToDo t;
        String[] filteredInput = input.trim().split(" ",2);
        if (filteredInput.length == 1) {
            throw new DueQuestException(DueQuestExceptionType.MISSING_DESCRIPTION);
        } else {
            t = new ToDo(filteredInput[1]);
        }
        return t;
    }

    /**
     * Used to validate and check for any errors in the user input.
     * for DeadLine object.
     * @param  input representing user input.
     * @return DeadLine object including the moduleCode of the deadline.
     * @throws DueQuestException if missing information or date is invalid.
     */
    public static Deadline validateDeadline(String input) throws WrongDateFormatException, InvalidDateException,
            EmptyArgumentException, MissingDeadlineTimingDetailsException {
        String[] filteredInput = input.trim().split(" ", 2);
        if (filteredInput.length == 1) {
            throw new EmptyArgumentException();
        } else if (!filteredInput[1].contains("/by")) {
            throw new MissingDeadlineTimingDetailsException();
        }
        String[] moduleCodeAndDescription = filteredInput[1].split("/by",2)[0].trim().split(" ", 2);
        String moduleCode = moduleCodeAndDescription[0].trim();
        String description = moduleCodeAndDescription[1].trim();
        String byInfo = filteredInput[1].split("/by", 2)[1].trim();
        if (byInfo.length() != 10) {
            throw new WrongDateFormatException();
        }
        if (LocalDate.parse(byInfo).isAfter(LocalDate.of(2021, 6, 1)) ||
            LocalDate.parse(byInfo).isBefore(LocalDate.of(2020, 10, 12))) {
            throw new InvalidDateException();
        }
        return new Deadline(moduleCode, description, byInfo);
    }

    /**
     * This is the new validateEvent method for our TP.
     * @param input the line input.
     * @return an Event object.
     * TODO
     *  - ADD EXCEPTIONS.
     */
    public static Event validateEvent(String input) {
        String filteredInput = input.trim().split(" ", 2)[1]; //get rid of event word in front
        String moduleCode = filteredInput.split(" ")[0];
        boolean isEventForAModule = verifyModuleCode(moduleCode);
        if (!isEventForAModule) {
            moduleCode = ""; // then moduleCode make it an empty string.
            // Aim is just to find a way such that this event wont be added to the module.
        }
        if (isEventForAModule) { //get rid of the module code if it is valid, means event belongs to a module
            filteredInput = filteredInput.split(" ", 2)[1];
        }
        String[] splitDescriptionAndDateTimeDetails = filteredInput.split("/at");
        String description = splitDescriptionAndDateTimeDetails[0].trim();
        String dateTimeLocationDetails = splitDescriptionAndDateTimeDetails[1];
        String[] splitDateTimeLocationDetails = dateTimeLocationDetails.trim().split(" ", 4);
        String dateOfEvent = splitDateTimeLocationDetails[0];
        String startTimeOfEvent = splitDateTimeLocationDetails[1];
        String endTimeOfEvent = splitDateTimeLocationDetails[2];
        String locationOfEvent = splitDateTimeLocationDetails[3];
        return new Event(description, moduleCode, locationOfEvent, startTimeOfEvent, endTimeOfEvent, dateOfEvent);
    }

    /**
     * Parses the Lesson object from input.
     * Lesson description moduleCode /on 4 (digit represent dayOfWeek), frequency, time.
     * Example: lesson lecture CS2113 /on 5 7 16:00 18:00.
     * @param input the line of the input
     * @return the Lesson object
     */
    public static Lesson parseLesson(String input) throws EmptyArgumentException, MissingLessonTimingException, InvalidModuleCodeException {
        String[] filteredInput = input.trim().split(" ", 2);

        if (filteredInput.length == 1) {  // e.g. lesson [empty_arguments]
            throw new EmptyArgumentException();
        }  else if (!filteredInput[1].contains("/on")) {
            throw new MissingLessonTimingException();
        }

        String[] descriptionWithModuleCode = filteredInput[1].split("/on", 2);
        String[] frequencyAndTime = descriptionWithModuleCode[1].trim().split(" ");
        String description = descriptionWithModuleCode[0].trim();
        descriptionWithModuleCode = descriptionWithModuleCode[0].trim().split(" ");
        int size = descriptionWithModuleCode.length;
        String moduleCode = descriptionWithModuleCode[size - 1].trim();
        if (!verifyModuleCode(moduleCode)) {
            throw new InvalidModuleCodeException();
        }
        if (frequencyAndTime.length != 3) {
            throw new MissingLessonTimingException();
        }
        description = description.substring(0, description.length() - moduleCode.length()).trim();
        int frequency = Integer.parseInt(frequencyAndTime[0]);
        String startTime = frequencyAndTime[1];
        String endTime = frequencyAndTime[2];
        return new Lesson(description, moduleCode, frequency, startTime, endTime);
    }
    /**
     * Used to validate the input in Delete Command
     * @param input
     * @return
     * @throws DueQuestException
     */
    public static DeleteCommand validateDeleteCommand(String input)throws DueQuestException {
        String[] filteredInput = input.trim().split(" ", 2);
        if (!input.contains("/date")) {
            return new DeleteCommand(filteredInput[1]);
        }
        try {
            String[] dateDetails = filteredInput[1].split("/date", 2);
            LocalDate specificDate = LocalDate.parse(dateDetails[1].trim().replace("/", "-"));
            return new DeleteCommand(dateDetails[0], specificDate);
        } catch (DateTimeException e) {
            throw new DueQuestException(DueQuestExceptionType.WRONG_DATE_FORMAT);
        }
    }

    /**
     * Parses DisplayCommand from the input.
     * @param input the user's input in string
     * @return DisplayCommand
     * @throws WrongDateFormatException if the date in wrong format and cannot be parsed
     * @throws InvalidArgumentsException if the input gives the argument in wrong format
     */
    public static DisplayCommand parseDisplayCommand(String input) throws WrongDateFormatException, InvalidArgumentsException{
        String moduleCode = "";
        String[] filteredInput = input.trim().split(" ",2);

        if(filteredInput.length==1){  // by default, the display time is now.
            return new DisplayCommand(LocalDate.now());
        }

        String[] descriptionWithModuleCode = filteredInput[1].trim().split(" ", 2);
        if(!descriptionWithModuleCode[0].equals("") && !descriptionWithModuleCode[0].contains("/date")) {
            moduleCode = descriptionWithModuleCode[0].trim().toUpperCase();
            if( descriptionWithModuleCode.length == 1){
                return new DisplayCommand(moduleCode);
            }
        }

        if (input.contains("/date")) {
            //split the filtered input into description and date info
            String[] dateDetails = filteredInput[1].split("/date",2);
            if (dateDetails[1].contains("-")){
                String[] dateRange = dateDetails[1].trim().split("-", 2);
                try{
                    LocalDate startDate = LocalDate.parse(dateRange[0].trim().replace("/","-"));
                    LocalDate endDate = LocalDate.parse(dateRange[1].trim().replace("/","-"));
                    return new DisplayCommand(startDate,endDate);
                } catch (DateTimeException e){
                    throw new WrongDateFormatException();
                }
            } else if (!moduleCode.equals("")) {
                try {
                    LocalDate specificDate = LocalDate.parse(dateDetails[1].trim().replace("/","-"));
                    return new DisplayCommand(moduleCode, specificDate);
                } catch (Exception e) {
                    throw new WrongDateFormatException();
                }
            } else {
                try {
                    LocalDate specificDate = LocalDate.parse(dateDetails[1].trim().replace("/","-"));
                    return new DisplayCommand(specificDate);
                } catch (Exception e) {
                    throw new WrongDateFormatException();
                }
            }
        }
        throw new InvalidArgumentsException();
    }

    /**
     * Method to check if a given moduleCode in String format is a valid Module code.
     * A valid Module examples. CS2113, CS2113T, DSA4211. Basically got 3 styles.
     * This method is mainly for the parser.
     * @param moduleCode moduleCode in string form that you want to verify if it is valid.
     * @return true if valid, else false.
     * TODO
     * 	- refactor this ugly code later.
     */
    public static boolean verifyModuleCode(String moduleCode) {
        if (moduleCode.length() < 6 || moduleCode.length() > 7) {
            return false;
        }
        char[] charArray = moduleCode.toCharArray();
        if (charArray.length == 6) {
            for (int i = 0; i < 2; i++) {
                char ch = charArray[i];
                if (!(ch >= 'A' && ch <= 'Z')) {
                    return false;
                }
            }
            for (int i = 2; i < 6; i++) {
                char ch = charArray[i];
                if (!(ch >= '0' && ch <= '9')) {
                    return false;
                }
            }
        } else if (charArray.length == 7) {
            // case of if it is like CS2113T
            if (charArray[2] >= '0' && charArray[2] <= '9') {
                for (int i = 0; i < 2; i++) {
                    char ch = charArray[i];
                    if (!(ch >= 'A' && ch <= 'Z')) {
                        return false;
                    }
                }
                for (int i = 2; i < 6; i++) {
                    char ch = charArray[i];
                    if (!(ch >= '0' && ch <= '9')) {
                        return false;
                    }
                }
                if (!(charArray[6] >= 'A' && charArray[6] <= 'Z')) {
                    return false;
                }
            } else { // case if DSA4211
                for (int i = 0; i < 3; i++) {
                    char ch = charArray[i];
                    if (!(ch >= 'A' && ch <= 'Z')) {
                        return false;
                    }
                }
                for (int i = 3; i < 7; i++) {
                    char ch = charArray[i];
                    if (!(ch >= '0' && ch <= '9')) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static EditTaskCommand validateEditTaskCommand (String input) throws WrongDateFormatException {
        String filteredInput = input.substring(5);
        String[] name = filteredInput.trim().split("/date",2);
        String[] property = name[1].trim().substring(10).trim().split("/",3);

        String description = name[0].trim();
        String type = (property[1].toLowerCase()).trim();
        String newValue = property[2].trim();
        switch (type){
            case "description":
                //Fall through
            case "tasktype":
                //Fall through
            case "module code":
                //Fall through
            case "modulecode":
                //Fall through
            case "time":
                try {
                    LocalDate date = LocalDate.parse(name[1].trim().substring(0, 10).trim().replace("/", "-"));
                    return new EditTaskCommand(description, date, type, newValue);
                }catch (DateTimeException e){
                    throw new WrongDateFormatException();
                }
            case "frequency":
                //int[] newFrequency = new int[2];
                //newFrequency[0] = Integer.parseInt(newValue);
                int newFrequency = Integer.parseInt(newValue);
                try {
                    LocalDate date = LocalDate.parse(name[1].trim().substring(0, 10).trim().replace("/", "-"));
                    return new EditTaskCommand(description, date, type, newFrequency);
                }catch (DateTimeException e){
                    throw new WrongDateFormatException();
                }
            case "date":
                try {
                    LocalDate date = LocalDate.parse(name[1].trim().substring(0, 10).trim().replace("/", "-"));
                    LocalDate newDate = LocalDate.parse(newValue.trim().replace("/","-"));
                    return new EditTaskCommand(description, date, type, newDate);
                }catch (DateTimeException e){
                    throw new WrongDateFormatException();
                }
            default:
                System.out.println("Wrong type");
                System.out.println(type);
                return null;
        }
    }
}
