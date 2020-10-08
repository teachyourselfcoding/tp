package seedu;

import seedu.command.ExitCommand;
import seedu.command.ListCommand;
import seedu.command.FindCommand;
import seedu.command.DoneCommand;
import seedu.command.AddCommand;
import seedu.command.DeleteCommand;
import seedu.command.Command;
import seedu.task.Deadline;
import seedu.task.ToDo;
import seedu.task.Event;

/**
 *Parser Object is used for translating String user input into
 * a actionable Command object for execution
 */
public class Parser {
    public Parser(){}
    /**
     * Convert the given string input into a subclass of Command class.
     * return different subclass of Command class
     *
     * @param input User input
     * @return a subclass of Command class
     * @throws DukeException if invalid input
     */
    public static Command parse(String input) throws DukeException {
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
                ToDo todo = validateToDo(words);
                //Fallthrough
                return new AddCommand(todo);
            case "deadline":
                Deadline deadline = validateDeadline(words);
                //Fallthrough
                return new AddCommand(deadline);
            case "event":
                Event ev = validateEvent(words);
                //Fallthrough
                return new AddCommand(ev);
            default:
                throw new DukeException(DukeExceptionType.INVALID_COMMAND);
            }
    }

    /**
     * Used to validate and check for any errors in the user input
     * for ToDo object
     *
     * @param  words representing user input
     * @return Todo object
     * @throws DukeException if missing information
     */
    public static ToDo validateToDo(String[] words) throws DukeException {
        ToDo t;
        String description="";
        if(words.length==1){
            throw new DukeException(DukeExceptionType.MISSING_DESCRIPTION);
        }
        else{
            for (int j = 1; j < words.length; j++) {
                description += words[j] + " ";
            }
            t = new ToDo(description);
        }
        return t;
    }

    /**
     * Used to validate and check for any errors in the user input
     * for DeadLine object
     *
     * @param  words representing user input
     * @return DeadLine object
     * @throws DukeException if missing information
     */
    public static Deadline validateDeadline(String[] words) throws DukeException {
        Deadline d;
        String description="";
        if(words.length==1){
            throw new DukeException(DukeExceptionType.MISSING_DESCRIPTION);
        }
        else{
            int byPosition=0;
            String byDescription="";
            for(int j =1; j< words.length;j++){
                if(words[j].contains("/by")){
                    byPosition=j;
                    break;
                }
                else{
                    description+= words[j]+ " ";
                }
            }
            if(byPosition!=0){
                for (int k = byPosition+1; k < words.length; k++) {
                    byDescription= byDescription +" "+ words[k];
                }
                d= new Deadline(description,byDescription);
            }
            else{
                throw new DukeException(DukeExceptionType.MISSING_DEADLINE);
            }
        }
        return d;
    }

    /**
     * Used to validate and check for any errors in the user input
     * for Event object
     *
     * @param  words representing user input
     * @return Event object
     * @throws DukeException if missing information
     */
    public static Event validateEvent(String[] words) throws DukeException {
        Event e;
        String description="";
        if(words.length==1) {
            throw new DukeException(DukeExceptionType.MISSING_DESCRIPTION);
        }
        else {
            int atPosition=0;
            String atDescription="";
            for(int j =1; j< words.length;j++) {
                if(words[j].contains("/at")) {
                    atPosition=j;
                    break;
                }
                else {
                    description+= words[j]+ " ";
                }
            }
            if(atPosition!=0) {
                for (int k = atPosition+1; k < words.length; k++) {
                    atDescription= atDescription +" "+ words[k];
                }
                e= new Event(description,atDescription);
            }
            else {
                throw new DukeException(DukeExceptionType.MISSING_EVENT_INFO);
            }
        }
        return e;
    }
}
