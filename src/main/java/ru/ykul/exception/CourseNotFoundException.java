package ru.ykul.exception;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(int id) {
        super("Could not find course " + id);
    }
}
