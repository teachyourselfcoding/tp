package seedu;

import seedu.command.*;
import seedu.task.Deadline;
import seedu.task.Lesson;
import seedu.task.ToDo;
import seedu.task.Event;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 *Parser Object is used for translating String user input into.
 * a actionable Command object for execution.
 */
public class Parser {
    public Parser(){}
    /**
     * Convert the given string input into a subclass of Command class.
     * return different subclass of Command class.
     * @param input User input.
     * @return a subclass of Command class.
     * @throws DueQuestException if invalid input.
     */
    public static Command parse(String input) throws DueQuestException {
        int taskNum;
        String[] words = input.split(" ");
            switch (words[0].toLowerCase()){
            case "bye":
                //Fallthrough
                return new ExitCommand();
            case "list":
                //Fallthrough
                return new ListCommand();
            case "done":
                taskNum = Integer.parseInt(words[1]);
                //Fallthrough
                return  new DoneCommand(taskNum-1);
            case "delete":
                taskNum = Integer.parseInt(words[1]);
                //Fallthrough
                return new DeleteCommand(taskNum-1);
            case "find":
                String[] sentence = input.toLowerCase().split(" ",2);
                String keywords=sentence[1];
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
                    return validateDisplayCommand(input);
            case "lesson":
                Lesson lesson = validateLesson(input);
                return new AddCommand(lesson);
            default:
                throw new DueQuestException(DueQuestExceptionType.INVALID_COMMAND);
            }
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
     * @return DeadLine object.
     * @throws DueQuestException if missing information.
     */
    public static Deadline validateDeadline(String input) throws DueQuestException {
        Deadline d;
        String[] filteredInput = input.trim().split(" ",2);
        if (filteredInput.length == 1) {
            throw new DueQuestException(DueQuestExceptionType.MISSING_DESCRIPTION);
        }  else if (!filteredInput[1].contains("/by")) {
            throw new DueQuestException(DueQuestExceptionType.MISSING_DEADLINE);
        } else {
            String[] descriptByFilter = filteredInput[1].split("/by",2);
            String byInfo = parseForDate(descriptByFilter[1]);
            d = new Deadline(descriptByFilter[0],byInfo);
        }
        return d;
    }

    /**
     * This is the new validateEvent method for our TP.
     * @param input the line input.
     * @return an Event object.
     * TODO
     *  - Handle exceptions.
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
        String[] splitDateTimeLocationDetails = dateTimeLocationDetails.trim().split(" ", 3);
        String dateOfEvent = splitDateTimeLocationDetails[0];
        String timeOfEvent = splitDateTimeLocationDetails[1];
        String locationOfEvent = splitDateTimeLocationDetails[2];
        return new Event(description, moduleCode, locationOfEvent, timeOfEvent, dateOfEvent);
    }

    /**
     * This is the old validateEvent method.
     */
    /*
    /**
     * Used to validate and check for any errors in the user input
     * for Event object.
     * @param  input representing user input.
     * @return Event object.
     * @throws DueQuestException if missing information.

    public static Event validateEvent(String input) throws DueQuestException {
        Event e;
        String[] filteredInput = input.trim().split(" ",2);
        if (filteredInput.length == 1) {
            throw new DueQuestException(DueQuestExceptionType.MISSING_DESCRIPTION);
        }  else if (!filteredInput[1].contains("/at")) {
            throw new DueQuestException(DueQuestExceptionType.MISSING_EVENT_INFO);
        } else {
            String[] descriptAtFilter = filteredInput[1].split("/at",2);
            String atInfo = parseForDate(descriptAtFilter[1]);
            e = new Event(descriptAtFilter[0], atInfo);
        }
        return e;
    }
    */

    /**
     * How to add a lesson object through input?
     * To Validate a lesson object.
     * Lesson description modulecode /on 4 (digit represent dayOfWeek), frequency, time.
     * Example: lesson lecture CS2113 /on 5 7 16:00 18:00.
     * @param input the line of the input
     * @return the Lesson object.
     * @throws DueQuestException settle later.
     */
    public static Lesson validateLesson(String input) {
        String[] filteredInput = input.trim().split(" ", 2);
        String[] descriptionWithModuleCode = filteredInput[1].split("/on", 2);
        String[] frequncyAndTime = descriptionWithModuleCode[1].trim().split(" ");
        String description = descriptionWithModuleCode[0].trim();
        descriptionWithModuleCode = descriptionWithModuleCode[0].trim().split(" ");
        int size = descriptionWithModuleCode.length;
        String moduleCode = descriptionWithModuleCode[size - 1];
        description = description.substring(0, description.length() - moduleCode.length()).trim();
        int[] frequency = new int[2];
        frequency[0] = Integer.parseInt(frequncyAndTime[0]);
        frequency[1] = Integer.parseInt(frequncyAndTime[1]);
        String startTime = frequncyAndTime[2];
        String endTime = frequncyAndTime[3];
        return new Lesson(description, moduleCode, frequency, startTime, endTime);
    }

    /**
     * Used to validate the input in Display Command
     * @param input
     * @return
     * @throws DueQuestException
     */
    public static DisplayCommand validateDisplayCommand(String input) throws DueQuestException{
        String moduleCode = "";
        String[] filteredInput = input.trim().split(" ",2);
        if(filteredInput.length==1){
            return new DisplayCommand(LocalDate.now());
        }
        String[] descriptionWithModuleCode = filteredInput[1].trim().split(" ", 2);
        if( !descriptionWithModuleCode[0].equals("")&&!descriptionWithModuleCode[0].contains("/date")) {
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
                    throw new DueQuestException(DueQuestExceptionType.WRONG_DATE_FORMAT);
                }
            } else if (!moduleCode.equals("")) {
                try {
                    LocalDate specificDate = LocalDate.parse(dateDetails[1].trim().replace("/","-"));
                    return new DisplayCommand(moduleCode, specificDate);
                } catch (Exception e) {
                    throw new DueQuestException(DueQuestExceptionType.WRONG_DATE_FORMAT);
                }
            } else {
                try {
                    LocalDate specificDate = LocalDate.parse(dateDetails[1].trim().replace("/","-"));
                    return new DisplayCommand(specificDate);
                } catch (Exception e) {
                    throw new DueQuestException(DueQuestExceptionType.WRONG_DATE_FORMAT);
                }
            }
        }
            throw new DueQuestException(DueQuestExceptionType.WRONG_INPUT_FORMAT);

    }

    public static String parseForDate(String input)  throws DueQuestException {
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("d-MM-yyyy");
            LocalDate dateFormatted = LocalDate.parse(input.trim(), df);
            return dateFormatted.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        } catch (DateTimeException e) {
            throw new DueQuestException(DueQuestExceptionType.WRONG_DATE_FORMAT);
        }
    }



    /**
     * Method to check if a given moduleCode in String format is a valid Module code.
     * A valid Module examples. CS2113, CS2113T, DSA4211. Basically got 3 styles.
     * This method is mainly for the parser.
     * @param moduleCode moduleCode in string form that you want to verify if it is valid.
     * @return true if valid, else false.
     * TODO
     * 	- refractor this ugly code later.
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
            } else { // case if DSA 4211
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
}
