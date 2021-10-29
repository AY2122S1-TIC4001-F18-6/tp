package task;

import constant.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Assignment extends Task {
    private final String type;
    private Date due_on;
    private String marks;

    public Assignment(String description, Date due_on, String marks) {
        super(description);
        type = "100";
        this.marks = marks;
        this.due_on = due_on;
    }

    public String taskToStringFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat(Utils.DATE_TIME_FORMAT);

        return "[" + this.getType() + "]" + "[" + super.getStatusIcon() + "] "
            + super.getDescription() + "," + " due on " + formatter.format(this.due_on) + ". "
            + "Marks: " + this.marks;
    }

    public String getType() {
        return this.type;
    }

    public String getLocation() {
        return this.marks;
    }

    public void setLocation(String location) {
        this.marks = marks;
    }

    public void setTime(Date time) {
        this.due_on = time;
    }
}
