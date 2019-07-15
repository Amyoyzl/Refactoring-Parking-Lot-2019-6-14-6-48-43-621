package com.thoughtworks.tdd.excception;

public class TicketMissingException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Please provide your parking ticket.";
    }
}
