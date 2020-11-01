package seedu;


import seedu.command.*;

import seedu.exception.*;

import java.time.DateTimeException;
import seedu.task.Deadline;
import seedu.task.Event;
import seedu.task.Lesson;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
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
        try {
            String[] words = input.split(" ");

            switch (words[0].toLowerCase()) {
                case "help":
                    //fallthrough
                    return new HelpCommand();
                case "bye":
                    //Fallthrough
                    return new ExitCommand();
                case "delete":
                    //Fallthrough
                    return validateDeleteCommand(input);
                case "edit":
                    return validateEditCommand(input);
                case "find":
                    String[] sentence = input.toLowerCase().split(" ", 2);
                    String keywords = sentence[1];
                    //Fallthrough
                    return new FindCommand(keywords);
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
        } catch (MissingEventDateAndTimeDetailsException e) {
            Ui.printMissingEventDateAndTimeDetailsMessage();
        } catch (InvalidTimeFormatException e) {
            Ui.printWrongTimeFormatMessage();
        } catch (InvalidFrequencyException e) {
            Ui.printInvalidFrequencyMessage();
        } catch (StartAndEndTimeSameException e) {
            Ui.printStartAndEndTimeCannotBeTheSameMessage();
        } catch (MissingDeadlineDescriptionException e) {
            Ui.printMissingDeadlineDescriptionMessage();
        } catch (MissingLessonDescriptionException e) {
            Ui.printMissingLessonDescriptionMessage();
        } catch (MissingEventDescriptionException e) {
            Ui.printMissingEventDescriptionMessage();
        } catch (StartTimeIsAfterEndTimeException e) {
            Ui.printStartTimeCannotBeAfterEndTimeMessage();
        } catch (StartTimeAndEndTimeTooEarlyException e) {
            Ui.printStartTimeAndEndTimeCannotBeBeforeEightOClockMessage();
        } catch (MissingModuleCodeOrInvalidModuleCodeException e) {
            Ui.printMissingModuleCodeOrInvalidModuleCodeMessage();
        } catch (Exception e) {
            Ui.printInvalidInputMessage();
        }
        return null;  // the function must return something
    }

    /**
     * Used to validate a deadline by checking for any errors in the user input for a Deadline.
     * @param input representing user input
     * @return DeadLine object including the moduleCode of the deadline.
     * @throws WrongDateFormatException if date in the input is of wrong format.
     * @throws InvalidDateException if date in the input is in an invalid range.
     * @throws EmptyArgumentException if the information of the Deadline is missing in the input.
     * @throws MissingDeadlineTimingDetailsException  if the timing details of the deadline is missing.
     * @throws InvalidModuleCodeException if the module code in the input is invalid.
     * @throws InvalidDateFormatException if the input date is of invalid format.
     */
    public static Deadline validateDeadline(String input) throws WrongDateFormatException, InvalidDateException,
            EmptyArgumentException, MissingDeadlineTimingDetailsException, InvalidModuleCodeException,
            MissingDeadlineDescriptionException {
        String[] filteredInput = input.trim().split(" ", 2);
        if (filteredInput.length == 1) {
            throw new EmptyArgumentException();
        } else if (!filteredInput[1].contains("/by")) {
            throw new MissingDeadlineTimingDetailsException();
        }
        String[] moduleCodeAndDescription = filteredInput[1].split("/by",2)[0].trim().split(" ", 2);
        if (moduleCodeAndDescription.length == 1) {
            throw new MissingDeadlineDescriptionException();
        }
        String moduleCode = moduleCodeAndDescription[0].trim();
        if (!verifyModuleCode(moduleCode)) {
            throw new InvalidModuleCodeException();
        }
        String description = moduleCodeAndDescription[1].trim();
        String byInfo = filteredInput[1].split("/by", 2)[1].trim();
        if (byInfo.length() != 10) {
            throw new WrongDateFormatException();
        }
        try {
            LocalDate.parse(byInfo);
        } catch (DateTimeParseException e) {
            throw new WrongDateFormatException();
        }
        if (LocalDate.parse(byInfo).isAfter(LocalDate.of(2021, 6, 1)) ||
            LocalDate.parse(byInfo).isBefore(LocalDate.of(2020, 10, 12))) {
            throw new InvalidDateException();
        }
        return new Deadline(moduleCode, description, byInfo);
    }

    /**
     * Used to validate a Event by checking for any errors in the user input.
     * @param input representing user input
     * @return Event object.
     * @throws WrongDateFormatException if the input date is of wrong format.
     * @throws InvalidDateException if the input date is not in the given range as stated in the User Guide.
     * @throws EmptyArgumentException if input does not contain anything after the first word.
     * @throws MissingEventDateAndTimeDetailsException if the event is missing time, date or location details.
     * @throws InvalidTimeFormatException if the input time is of invalid format.
     * @throws StartAndEndTimeSameException if the start and end time is the same.
     * @throws MissingEventDescriptionException if the event description is missing.
     * @throws StartTimeIsAfterEndTimeException if the start time is later than the end time.
     * @throws StartTimeAndEndTimeTooEarlyException if start time or end time is before 8am.
     * @throws MissingModuleCodeOrInvalidModuleCodeException if module code is missing or invalid.
     */
    public static Event validateEvent(String input) throws WrongDateFormatException, InvalidDateException,
            EmptyArgumentException, MissingEventDateAndTimeDetailsException, InvalidTimeFormatException,
            StartAndEndTimeSameException, MissingEventDescriptionException,
            StartTimeIsAfterEndTimeException, StartTimeAndEndTimeTooEarlyException,
            MissingModuleCodeOrInvalidModuleCodeException {
        String[] filteredInputTest = input.trim().split(" ", 2);
        if (filteredInputTest.length == 1) {
            throw new EmptyArgumentException();
        } else if (!filteredInputTest[1].contains("/at")) {
            throw new MissingEventDateAndTimeDetailsException();
        }
        String filteredInput = input.trim().split(" ", 2)[1]; //get rid of event word in front
        String moduleCode = filteredInput.split(" ")[0];

        String[] splitDescriptionAndDateTimeDetails = filteredInput.split("/at");
        String description = splitDescriptionAndDateTimeDetails[0].trim();

        if (splitDescriptionAndDateTimeDetails[0].split(" ").length == 1) {
            throw new MissingEventDescriptionException();
        }
        if (!verifyModuleCode(moduleCode)) {
            throw new MissingModuleCodeOrInvalidModuleCodeException();
        }
        description = description.split(" ", 2)[1].trim();
        String dateTimeLocationDetails = splitDescriptionAndDateTimeDetails[1];
        String[] splitDateTimeLocationDetails = dateTimeLocationDetails.trim().split(" ", 4);
        if (splitDateTimeLocationDetails.length != 4) {
            throw new MissingEventDateAndTimeDetailsException();
        }
        String dateOfEvent = splitDateTimeLocationDetails[0];
        if (dateOfEvent.length() != 10) {
            throw new WrongDateFormatException();
        }
        try {
            LocalDate.parse(dateOfEvent);
        } catch (DateTimeParseException e) {
            throw new WrongDateFormatException();
        }

        if (LocalDate.parse(dateOfEvent).isAfter(LocalDate.of(2021, 6, 1)) ||
                LocalDate.parse(dateOfEvent).isBefore(LocalDate.of(2020, 10, 12))) {
            throw new InvalidDateException();
        }
        String startTime = splitDateTimeLocationDetails[1];
        String endTime = splitDateTimeLocationDetails[2];
        String locationOfEvent = splitDateTimeLocationDetails[3];

        if (startTime.length() != 5 || endTime.length() != 5) {
            throw new InvalidTimeFormatException();
        }
        String hhStart = startTime.substring(0, 2);
        String mmStart = startTime.substring(3);
        String hhEnd = endTime.substring(0, 2);
        String mmEnd = endTime.substring(3);

        try {
            LocalTime.of(Integer.parseInt(hhStart), Integer.parseInt(mmStart));
        } catch (NumberFormatException e) {
            throw new InvalidTimeFormatException();
        } catch (DateTimeException e) {
            throw new InvalidTimeFormatException();
        }
        if (Integer.parseInt(mmStart) != 0) {
            throw new InvalidTimeFormatException();
        }
        try {
            LocalTime.of(Integer.parseInt(hhEnd), Integer.parseInt(mmEnd));
        } catch (NumberFormatException e) {
            throw new InvalidTimeFormatException();
        } catch (DateTimeException e) {
            throw new InvalidTimeFormatException();
        }

        if (Integer.parseInt(mmEnd) != 0) {
            throw new InvalidTimeFormatException();
        }
        if (startTime.equals(endTime)) {
            throw new StartAndEndTimeSameException();
        }
        if (LocalTime.of(Integer.parseInt(hhStart), Integer.parseInt(mmStart)).
                isAfter(LocalTime.of(Integer.parseInt(hhEnd), Integer.parseInt(mmEnd)))) {
            throw new StartTimeIsAfterEndTimeException();
        }
        if (LocalTime.of(Integer.parseInt(hhStart), Integer.parseInt(mmStart)).isBefore(LocalTime.of(8, 0)) ||
                LocalTime.of(Integer.parseInt(hhEnd), Integer.parseInt(mmEnd)).isBefore(LocalTime.of(8,0))) {
            throw new StartTimeAndEndTimeTooEarlyException();
        }
        return new Event(description, moduleCode, locationOfEvent, startTime, endTime, dateOfEvent);
    }

    /**
     * Used to validate a Lesson by checking for any errors in the user input.
     * @param input representing user input.
     * @return Lesson object.
     * @throws EmptyArgumentException if the information of the Lesson is missing in the input.
     * @throws MissingLessonTimingException if the timing of the lesson is missing in the input.
     * @throws InvalidModuleCodeException if the module code in the input is invalid.
     * @throws InvalidTimeFormatException if the time in the input is of wrong format.
     * @throws InvalidFrequencyException if the frequency in the input is invalid.
     * @throws StartAndEndTimeSameException if the start time and end time is the same.
     * @throws MissingLessonDescriptionException if the lesson description is missing.
     * @throws StartTimeIsAfterEndTimeException if the start time is after the end time.
     * @throws StartAndEndTimeSameException if the start time and end time are the same.
     * @throws StartTimeAndEndTimeTooEarlyException if the start or end time is before 8am.
     */
    public static Lesson parseLesson(String input) throws EmptyArgumentException, MissingLessonTimingException,
            InvalidModuleCodeException, InvalidTimeFormatException, InvalidFrequencyException,
            StartAndEndTimeSameException, MissingLessonDescriptionException, StartTimeIsAfterEndTimeException,
            StartTimeAndEndTimeTooEarlyException {
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
        if (descriptionWithModuleCode.length == 1) {
            throw new MissingLessonDescriptionException();
        }
        if (frequencyAndTime.length != 3) {
            throw new MissingLessonTimingException();
        }
        description = description.substring(0, description.length() - moduleCode.length()).trim();
        String freq = frequencyAndTime[0];
        try {
            Integer.parseInt(freq);
        } catch (NumberFormatException e) {
            throw new InvalidFrequencyException();
        }
        int frequency = Integer.parseInt(frequencyAndTime[0]);
        if (frequency > 7 || frequency < 1) {
            throw new InvalidFrequencyException();
        }
        String startTime = frequencyAndTime[1];
        String endTime = frequencyAndTime[2];
        if (startTime.length() != 5 || endTime.length() != 5) {
            throw new InvalidTimeFormatException();
        }
        String hhStart = startTime.substring(0, 2);
        String mmStart = startTime.substring(3);
        String hhEnd = endTime.substring(0, 2);
        String mmEnd = endTime.substring(3);
        try {
            LocalTime.of(Integer.parseInt(hhStart), Integer.parseInt(mmStart));
        } catch (NumberFormatException e) {
            throw new InvalidTimeFormatException();
        } catch (DateTimeException e) {
            throw new InvalidTimeFormatException();
        }
        if (Integer.parseInt(mmStart) != 0) {
            throw new InvalidTimeFormatException();
        }
        try {
            LocalTime.of(Integer.parseInt(hhEnd), Integer.parseInt(mmEnd));
        } catch (NumberFormatException e) {
            throw new InvalidTimeFormatException();
        } catch (DateTimeException e) {
            throw new InvalidTimeFormatException();
        }
        if (Integer.parseInt(mmEnd) != 0) {
            throw new InvalidTimeFormatException();
        }
        if (startTime.equals(endTime)) {
            throw new StartAndEndTimeSameException();
        }
        if (LocalTime.of(Integer.parseInt(hhStart), Integer.parseInt(mmStart)).
                isAfter(LocalTime.of(Integer.parseInt(hhEnd), Integer.parseInt(mmEnd)))) {
            throw new StartTimeIsAfterEndTimeException();
        }
        if (LocalTime.of(Integer.parseInt(hhStart), Integer.parseInt(mmStart)).isBefore(LocalTime.of(8, 0)) ||
                LocalTime.of(Integer.parseInt(hhEnd), Integer.parseInt(mmEnd)).isBefore(LocalTime.of(8,0))) {
            throw new StartTimeAndEndTimeTooEarlyException();
        }
        return new Lesson(description, moduleCode, frequency, startTime, endTime);
    }
    /**
     * Used to validate the input in Delete Command.
     * @param input representing user input.
     * @return returns a DeleteCommand.
     * @throws DueQuestException if the input date is of wrong format.
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
     * @return DisplayCommand.
     * @throws WrongDateFormatException if the date in wrong format and cannot be parsed.
     * @throws InvalidArgumentsException if the input gives the argument in wrong format.
     */
    public static DisplayCommand parseDisplayCommand(String input) throws WrongDateFormatException, InvalidArgumentsException {
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

    /**
     * Checks and parsers editCommand from input.
     * @param input user's input string
     * @return editCommand
     * @throws WrongDateFormatException
     */
    public static editCommand validateEditCommand(String input) throws WrongDateFormatException {
        String moduleCode = null;
        String[] splitViaModule;
        String filteredInput;

        if(input.charAt(5)=='c'&& input.charAt(6)=='/'){
            splitViaModule = ((input.split("c/"))[1].trim()).split(" ",2);
            filteredInput = splitViaModule[1].trim();
            moduleCode = splitViaModule[0].trim();

            if(!filteredInput.contains("/date")){
                String moduleProperty = filteredInput.trim().split("/",2)[0].trim();
                String newModuleProperty = filteredInput.trim().split("/")[1].trim();
                if(
                        moduleProperty.equals("staff")||
                                moduleProperty.equals("au")||
                                moduleProperty.equals("modulecode")
                ){
                    Storage.getStorage().exportAdditionalData(input);
                    return new EditModuleCommand(moduleCode, moduleProperty, newModuleProperty);
                }
                else{
                    System.out.println("Invalid input");
                }
            }

        }
        else{
            filteredInput = input.substring(5);
        }


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
                if(moduleCode == null){
                    Storage.getStorage().exportAdditionalData(input);
                    return new EditTaskCommand(description, date, type, newValue);
                } System.out.println("test 1");
                Storage.getStorage().exportAdditionalData(input);
                return new EditModuleCommand(moduleCode, description, date, type, newValue);
            }catch (DateTimeException e){
                throw new WrongDateFormatException();
            }
        case "frequency":
            //int[] newFrequency = new int[2];
            //newFrequency[0] = Integer.parseInt(newValue);
            int newFrequency = Integer.parseInt(newValue);
            try {
                LocalDate date = LocalDate.parse(name[1].trim().substring(0, 10).trim().replace("/", "-"));
                if(moduleCode == null){
                    Storage.getStorage().exportAdditionalData(input);
                    return new EditTaskCommand(description, date, type, newFrequency);
                }
                Storage.getStorage().exportAdditionalData(input);
                return new EditModuleCommand(moduleCode, description, date, type, newFrequency);
            }catch (DateTimeException e){
                throw new WrongDateFormatException();
            }
        case "date":
            try {
                LocalDate date = LocalDate.parse(name[1].trim().substring(0, 10).trim().replace("/", "-"));
                LocalDate newDate = LocalDate.parse(newValue.trim().replace("/","-"));
                if(moduleCode == null){
                    Storage.getStorage().exportAdditionalData(input);
                    return new EditTaskCommand(description, date, type, newDate);
                }
                Storage.getStorage().exportAdditionalData(input);
                return new EditModuleCommand(moduleCode, description, date, type, newDate);
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
