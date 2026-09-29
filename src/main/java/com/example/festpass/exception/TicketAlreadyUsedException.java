package com.example.festpass.exception;

public class TicketAlreadyUsedException extends RuntimeException {

    public TicketAlreadyUsedException(String message) {
        super(message);
    }
}