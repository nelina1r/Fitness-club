package ru.t1.dedov.exceptions;

public class CardAlreadyAttachedException extends Exception{

    public CardAlreadyAttachedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CardAlreadyAttachedException(String msg) {
        super(msg);
    }
}
