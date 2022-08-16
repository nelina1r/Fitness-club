package ru.t1.dedov.exceptions;

public class InvalidRoleException extends Exception {

    public InvalidRoleException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InvalidRoleException(String msg) {
        super(msg);
    }
}
