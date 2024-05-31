package ru.ykul.exception;

public class GroupNotFoundException extends RuntimeException {

    public GroupNotFoundException(int id) {
        super("Could not find group " + id);
    }
}
