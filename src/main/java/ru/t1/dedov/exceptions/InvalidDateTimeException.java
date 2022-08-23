package ru.t1.dedov.exceptions;

public class InvalidDateTimeException extends Exception{

    public InvalidDateTimeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InvalidDateTimeException(String msg) {
        super(msg);
    }
}
