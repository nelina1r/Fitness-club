package ru.t1.dedov.exceptions;

public class InvalidCapacityException extends Exception{

    public InvalidCapacityException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InvalidCapacityException(String msg) {
        super(msg);
    }
}
