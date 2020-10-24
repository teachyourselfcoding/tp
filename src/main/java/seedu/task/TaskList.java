package seedu.task;


import seedu.Storage;
import seedu.Ui;

import java.io.IOException;
import java.util.ArrayList;
/****************** READ THIS FIRST
 * This class is no longer useful since the ScheduleManager is handling all the tasks.
 * We will need to implement the delete, edit and other commands stuff that are executed here
 * into the ScheduleManager instead.
 *
 * Tasklist class contain all the relevant function such as Adding, deleting.
 * and storing the list of task all in one object.
 */

public class TaskList {
    private  ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getIndex(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Print out all the task in a list form.
     */
    public void listContents() {
        if (tasks.size() == 0) {
            System.out.println("No list Detected, add some text!");
        } else {
            for (int j = 0; j < tasks.size(); j++) {
                int position = j + 1;
                System.out.println(position + "." + "[" + tasks.get(j).getTaskType() + "]" + "["
                        + tasks.get(j).getStatusIcon() + "] "
                        + tasks.get(j).getFullDescription());
            }
        }
        Ui.printSeparator();
    }

//    /**
//     * Based on the given index, delete corresponding item in the list.
//     * @param index position for deletion of the task in the list.
//     * @param storage seedu.Storage object.
//     */
//    public void deleteItemFromList(int index, Storage storage) {
//        System.out.println("Nice! I've removed this task:"
//                + "\n" + tasks.get(index).getTaskType()
//                + "["
//                + tasks.get(index).getStatusIcon()
//                + "] "
//                + tasks.get(index).getFullDescription()
//                + "\nNow you have " + (tasks.size() - 1)
//                + " tasks in the list."
//        );
//        tasks.remove(index);
//        try {
//            storage.updateFileContents(tasks);
//        } catch (IOException e) {
//            System.out.println("Problem with saving file!");
//        }
//        Ui.printSeparator();
//    }
}
