package parser;

import command.*;
import command.DeleteCommand;

import constant.CommandKeyWords;
import constant.ErrorMessage;

import exception.ErrorHandler;

public class Parser {
    private String content;
    private String taskNo;

    public Command parse(String input) throws ErrorHandler {
        String[] result = input.split(" ", 2);
        CommandKeyWords commandWord = CommandKeyWords.getEnum(result[0].toUpperCase());

        switch (commandWord) {
            case VIEW:
                return new ViewCommand();
            case TODO:
                if (result.length < 2) {
                    throw new ErrorHandler(ErrorMessage.EMPTY_TODO);
                }
                this.content = result[1].trim();
                return new TodoCommand(this.content);
            case DELETE:
                if (result.length < 2) {
                    throw new ErrorHandler(ErrorMessage.EMPTY_TASK_NUMBER);
                }
                this.taskNo = result[1].trim();

                return new DeleteCommand(this.taskNo);
            case APPOINTMENT:
                if (result.length < 2) {
                    throw new ErrorHandler(ErrorMessage.EMPTY_APPOINTMENT_DESCRIPTION);
                }
                return this.handleNewAppointment(result[1].trim());

            case SET_LOCATION:
                if (result.length < 2) {
                    throw new ErrorHandler(ErrorMessage.EMPTY_TASK_NUMBER);
                }
                String[] inputContent = result[1].split(" ", 2);

                if (inputContent.length < 2) {
                    throw new ErrorHandler(ErrorMessage.EMPTY_LOCATION_DESCRIPTION);
                }

                this.taskNo = inputContent[0];
                this.content = inputContent[1];

                return new LocationCommand(this.taskNo, this.content);
            case SET_TIME:
                if (result.length < 2) {
                    throw new ErrorHandler(ErrorMessage.EMPTY_TASK_NUMBER);
                }

                return this.handleSetTime(result[1]);
            case ASSIGNMENT:
                if (result.length < 2) {
                    throw new ErrorHandler(ErrorMessage.EMPTY_ASSIGNMENT_DESCRIPTION);
                }
                return this.handleNewAssignment(result[1].trim());
            case BYE:
            default:
                return new ByeCommand();
        }
    }

    private Command handleNewAppointment(String inputContent) throws ErrorHandler {
        String[] appointmentInput = inputContent.split("/at", 2);
        this.content = appointmentInput[0].trim();

        if (appointmentInput.length < 2) {
            throw new ErrorHandler(ErrorMessage.EMPTY_APPOINTMENT_TIME);
        }
        String[] timeContent = appointmentInput[1].split("/l", 2);

        if (timeContent.length < 2) {
            throw new ErrorHandler(ErrorMessage.EMPTY_APPOINTMENT_LOCATION);
        }

        String at = timeContent[0].trim();
        String location = timeContent[1].trim();

        return new AppointmentCommand(this.content, at, location);
    }

    private Command handleSetTime(String input) throws ErrorHandler {
        String[] inputContent = input.split(" ", 2);

        if (inputContent.length < 2) {
            throw new ErrorHandler(ErrorMessage.EMPTY_TIME_DESCRIPTION);
        }
        this.taskNo = inputContent[0];
        this.content = inputContent[1];

        return new AppointmentTimeCommand(this.taskNo, this.content);
    }

    private Command handleNewAssignment(String inputContent) throws ErrorHandler {
        String[] assignmentInput = inputContent.split("/due_on", 2);
        this.content = assignmentInput[0].trim();

        if (assignmentInput.length < 2) {
            throw new ErrorHandler(ErrorMessage.EMPTY_ASSIGNMENT_MARKS);
        }
        String[] timeContent = assignmentInput[1].split("/l", 2);

        if (timeContent.length < 2) {
            throw new ErrorHandler(ErrorMessage.EMPTY_ASSIGNMENT_DUE_DATE);
        }

        String due_on = timeContent[0].trim();
        String marks = timeContent[1].trim();

        return new AssignmentCommand(this.content, due_on, marks);
    }
}
