package seedu.task;

import java.time.DayOfWeek;
import java.time.LocalDate;


public class Lesson extends Task {
    private String startTime;
    private String endTime;
    private int frequency;
    private LocalDate specificDate;
    /**
     * Constructor of Lesson object.
     * @param description to say if it is a lecture or tutorial or lab etc
     * @param moduleCode module code
     * @param frequency tells us the day of the week. The lesson will be added to that particular day in every week.
     * @param startTime the start time of the lesson
     * @param endTime the end time of the lesson
     */
    public Lesson(String description, String moduleCode, int frequency, String startTime, String endTime) {
        super(description, moduleCode);
        super.taskType = "L";
        this.frequency = frequency;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * for quick adding of lessons
     * @param description
     * @param moduleCode
     * @param startTime
     * @param endTime
     * @param specificDate
     */
    public Lesson(String description, String moduleCode, String startTime, String endTime,LocalDate  specificDate) {
        super(description, moduleCode);
        super.taskType = "L";
        this.startTime = startTime;
        this.endTime = endTime;
        this.specificDate =specificDate;

    }

    /**
     * Method to get the day of the week where which the lesson will be held on.
     * @return this will return me the day in DayOfWeek. eg, return MONDAY, TUESDAY, WEDNESDAY, etc.
     */
    public DayOfWeek getLessonDayInDayOfWeek() {
        return DayOfWeek.of(this.frequency);
    }

    /**
     * Get the day of the lesson in the week in a String.
     * @return the day in String
     */
    public String getLessonDay() {
        return DayOfWeek.of(this.frequency).toString();
    }

    /**
     * TODO
     *  - might want to change the representation of the toString method.
     * @return string representation of the Lesson Object.
     */
    @Override
    public String toString() {
        return "[L] " + description + " - " + moduleCode + " " + this.getLessonDayInDayOfWeek() +
                " " + startTime + " " + endTime;
    }

    public String getStartTime(){
        return startTime;
    }

    public String getEndTime(){
        return endTime;
    }

    public LocalDate getDate(){
        return specificDate;
    }
}
