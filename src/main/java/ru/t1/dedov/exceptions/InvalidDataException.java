package ru.t1.dedov.exceptions;

public class InvalidDataException extends Exception{

    public InvalidDataException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InvalidDataException(String msg) {
        super(msg);
    }
}
