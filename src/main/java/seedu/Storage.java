package seedu;

import seedu.task.Deadline;
import seedu.task.Event;
import seedu.task.ToDo;
import seedu.task.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Storage class is used for creating and saving file on the local hard disk.
 * Also used for loading saved data on startup
 */
public class Storage {
    private String filePath;
    private ArrayList<Task> tasks;
    public Storage(String filePath){
        this.filePath=filePath;
    }

    /**
     * Attempt to load saved information on the hard disk
     *
     * @return a list of Task
     * @throws FileNotFoundException If there is no saved file in the specified location
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        tasks= new ArrayList<>();
        int dashPosition;
        int atPosition;
        String description;
        String taskIdentifiers;
        String timedateInfo;
        while(s.hasNext()){
            String contents=s.nextLine();
            dashPosition = contents.indexOf("-");
            taskIdentifiers=contents.substring(0,dashPosition);
            String[] words = contents.split("|");
            switch (words[0]){
            case "D":
                atPosition = contents.indexOf("@");
                description = contents.substring(dashPosition+1,atPosition);
                timedateInfo = contents.substring(atPosition+1);
                if(taskIdentifiers.contains("\u2713")){
                    tasks.add(new Deadline(description.trim(),timedateInfo.trim(),true));
                }
                else {
                    tasks.add(new Deadline(description.trim(),timedateInfo.trim()));
                }

                break;
            case "E":
                atPosition = contents.indexOf("@");
                description = contents.substring(dashPosition+1,atPosition);
                timedateInfo = contents.substring(atPosition+1);
                if(taskIdentifiers.contains("\u2713")){
                    tasks.add(new Event(description.trim(),timedateInfo.trim(),true));
                }
                else {
                    tasks.add(new Deadline(description.trim(),timedateInfo.trim()));
                }
                break;
            case "T":
                description = contents.substring(dashPosition+1);
                if(taskIdentifiers.contains("\u2713")){
                    tasks.add(new ToDo(description.trim(),true));
                }
                else {
                    tasks.add(new ToDo(description.trim()));
                }

                break;
            default: break;
            }
        }
        return tasks;
    }

    /**
     * Creates a new "duke.txt" in the data directory for new users
     */
    public void createSavedFile() {
        System.out.println("So we have create a duke.txt under the data directory");
        File f = new File("data");
        boolean isCreated =f.mkdir();
        File g =new File(filePath);

        if(isCreated){
            try{
                g.createNewFile();
            }catch(IOException e){
                System.out.println("Sorry Couldnt create save file!");
            }
        }else{
            System.out.println("Sorry Couldn’t create save file");
        }
        Ui.showDivider();
    }

    /**
     * Used to update or refresh the duke.txt contents for scenarios
     * where deletion or change in existing Information occurs
     *
     * @param t newly updated list
     * @throws IOException if the saved file is not found in its expected location
     */
    public void updateFileContents(ArrayList<Task> t) throws IOException {
        tasks=t;
        FileWriter fw0=new FileWriter(filePath);
        fw0.write("");
        FileWriter fw = new FileWriter(filePath,true);
        for(int i=0;i< tasks.size();i++) {
            if(tasks.get(i) instanceof Event) {
                fw.write(tasks.get(i).getTaskType()+"|"+ tasks.get(i).getStatusIcon()
                        +" - "+tasks.get(i).getDescription()+" @ " +((Event) tasks.get(i)).getLocation()
                        +System.lineSeparator());
            }
            else if(tasks.get(i) instanceof Deadline) {
                fw.write(tasks.get(i).getTaskType()+"|"+ tasks.get(i).getStatusIcon()
                        +" - "+tasks.get(i).getDescription()+" @ " +((Deadline) tasks.get(i)).getTimingInfo()
                        + System.lineSeparator());
            }
            else {
                fw.write(tasks.get(i).getTaskType()+"|"+ tasks.get(i).getStatusIcon()
                        +" - "+tasks.get(i).getDescription()+ System.lineSeparator());
            }
        }
        fw.close();
    }

    /**
     * Used to append to the duke.txt contents for scenarios
     * where adding of new data on top of existing data occurs.
     *
     * @param t newly updated list
     * @throws IOException if the saved file is not found in its expected location
     */
    public void appendToFile(Task t) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        if(t instanceof Event) {
            fw.write(t.getTaskType()+"|"+ t.getStatusIcon()
                    +" - "+t.getDescription()+" @ " +((Event) t).getLocation()
                    +System.lineSeparator());
        }
        else if(t instanceof Deadline) {
            fw.write(t.getTaskType()+"|"+ t.getStatusIcon()
                    +" - "+t.getDescription()+" @ " +((Deadline) t).getTimingInfo()
                    + System.lineSeparator());
        }
        else {
            fw.write(t.getTaskType()+"|"+ t.getStatusIcon()
                    +" - "+t.getDescription()+ System.lineSeparator());
        }
        fw.close();
    }
}
