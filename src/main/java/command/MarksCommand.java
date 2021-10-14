package command;

import exception.ErrorHandler;
import storage.Storage;
import list.TaskList;
import ui.Ui;

public class MarksCommand extends Command {
    private final int taskNumber;
    private final String Marks;

    public MarksCommand(String taskNumber, String marks) throws ErrorHandler {
        this.taskNumber = this.pareTaskNumber(taskNumber);
        this.Marks = marks;
    }

    public void execute(Storage storage, Ui ui, TaskList taskList) {
        System.out.println("pending implementation for setting marks for Assignment task. "
                + "Waiting for implementation of Assignment task");
    }

}
