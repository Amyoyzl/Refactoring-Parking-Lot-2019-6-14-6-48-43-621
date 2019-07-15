package com.thoughtworks.tdd;

public interface Parkable {
    Ticket park(Car car);
    Car fetch(Ticket ticket);

    boolean isFull();

    boolean isContainTicket(Ticket ticket);
}
