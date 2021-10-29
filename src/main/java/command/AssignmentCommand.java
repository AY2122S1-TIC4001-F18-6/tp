package command;

import exception.ErrorHandler;
import storage.Storage;
import task.Assignment;
import tasklist.TaskList;
import ui.Ui;

import java.util.Date;

public class AssignmentCommand extends Command {
    private final String taskDescription;
    private final Date due_on;
    private final String marks;

    public AssignmentCommand(String description, String at, String marks) throws ErrorHandler {
        this.taskDescription = description;
        this.due_on = this.parseDateTime(at);
        this.marks = marks;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        Assignment addedAssignment = new Assignment(this.taskDescription, this.due_on, this.marks);
        list.addItem(addedAssignment);

        ui.printAddedTask(addedAssignment.taskToStringFormat(), list.sizeOfTask());
    }
}
