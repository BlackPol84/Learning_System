package ru.ykul.exception;

public class ScheduleNotFoundException extends RuntimeException {

    public ScheduleNotFoundException(int id) {
        super("Could not find schedule " + id);
    }
}
