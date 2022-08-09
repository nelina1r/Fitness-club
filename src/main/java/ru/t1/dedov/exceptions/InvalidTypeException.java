package ru.t1.dedov.exceptions;

public class InvalidTypeException extends Exception{

    public InvalidTypeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InvalidTypeException(String msg) {
        super(msg);
    }
}
