package ru.t1.dedov.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidCapacityException.class)
    public String invalidCapacityExceptionHandler(){
        return "you picked invalid capacity number";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CardAlreadyAttachedException.class)
    public String cardAlreadyAttachedToClientExceptionHandler(){
        return "card already attached to client!";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDateTimeException.class)
    public String invalidDateTimeExceptionHandler(){
        return "you picked wrong date-time";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(InvalidRoleException.class)
    public String invalidRoleExceptionHandler(){
        return "invalid role of user";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidTypeException.class)
    public String invalidTypeExceptionHandler(){
        return "you picked wrong personType";
    }
}
