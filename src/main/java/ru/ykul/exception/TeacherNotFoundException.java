package ru.ykul.exception;

public class TeacherNotFoundException extends RuntimeException {

    public TeacherNotFoundException(int id) {
        super("Could not find teacher " + id);
    }
}
