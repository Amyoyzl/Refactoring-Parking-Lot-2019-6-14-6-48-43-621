package com.thoughtworks.tdd.excception;

public class NotEnoughPositionException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Not enough position.";
    }
}
