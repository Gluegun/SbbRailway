package ru.tsystems.school.service.exceptions;

public class CantDeleteException extends RuntimeException {

    public CantDeleteException(String message) {
        super(message);
    }
}
