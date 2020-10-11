package seedu.task;

import seedu.Module;

import java.time.DayOfWeek;


public class Lesson extends Task {
    /**
     *
     * @param description to say if it is a lecture or tutorial or lab etc
     * @param moduleCode module code
     * @param frequency in an array, tells us the day of the week of the event and frequncy
     * @param time the time of the event
     */
    public Lesson(String description, String moduleCode, int[] frequency, String time) {
        super(description, moduleCode, frequency, time);
        super.taskType = "L";
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
