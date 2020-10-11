package seedu.task;

import seedu.Module;

import java.time.DayOfWeek;


public class Lesson extends Task {
    private String startTime;
    private String endTime;
    /**
     *
     * @param description to say if it is a lecture or tutorial or lab etc
     * @param moduleCode module code
     * @param frequency in an array, tells us the day of the week of the event and frequency
     * @param startTime the start time of the lesson
     * @param endTime the end time of the lesson
     */
    public Lesson(String description, String moduleCode, int[] frequency, String startTime, String endTime) {
        super(description, moduleCode, frequency);
        super.taskType = "L";
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Note: frequency is in [dayOfWeek as in int, frequency]
     * Lets assume its weekly first, because if its biweekly, abit more
     * troublesome in terms of adding the lesson into the ShceduleManager.
     * Hence, frequncy will always be [an integer indicating day of week, 7] for now (where 7 represents 7 days.
     * @return
     */
    public String getLessonDay() {
        return DayOfWeek.of(this.frequency[1]).toString();
    }

    @Override
    public String toString() {
        return description + " " + moduleCode + " " + getLessonDay() + " " + time;
    }
}
