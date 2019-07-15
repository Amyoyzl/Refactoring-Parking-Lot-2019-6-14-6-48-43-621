package com.thoughtworks.tdd.excception;

public class UnrecognizedTicketException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Unrecognized parking ticket.";
    }
}
