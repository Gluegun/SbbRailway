package ru.tsystems.school.exceptions;

public class CantBuyTicketException extends RuntimeException {

    public CantBuyTicketException(String message) {
        super(message);
    }
}
