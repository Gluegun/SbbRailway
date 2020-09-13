package ru.tsystems.school.service.exceptions;

public class CantBuyTicketException extends RuntimeException {

    public CantBuyTicketException(String message) {
        super(message);
    }
}
