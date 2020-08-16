package ru.tsystems.school.exceptions;

public class CantDeleteException extends RuntimeException {

    public CantDeleteException(String message) {
        super(message);
    }
}
