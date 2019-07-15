package com.thoughtworks.tdd;

import com.thoughtworks.tdd.excception.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot implements Parkable {

    private final int CAPACITY;
    private Map<Ticket, Car> ticketCarMap;

    public ParkingLot(int capacity) {
        this.CAPACITY = capacity;
        ticketCarMap = new HashMap<>(CAPACITY);
    }

    @Override
    public boolean isFull() {
        return CAPACITY == ticketCarMap.size();
    }

    @Override
    public Ticket park(Car car) {
        if (getAvailablePosition() > 0) {
            Ticket ticket = new Ticket();
            ticketCarMap.put(ticket, car);
            return ticket;
        }
        return null;
    }

    public int getAvailablePosition() {
        return CAPACITY - ticketCarMap.size();
    }

    public double getAvailablePositionRate() {
        return getAvailablePosition() / (double)CAPACITY;
    }

    @Override
    public Car fetch(Ticket ticket) {
        if (isContainTicket(ticket)) {
            return ticketCarMap.remove(ticket);
        } else {
            throw new UnrecognizedTicketException();
        }
    }

    public boolean isContainTicket(Ticket ticket) {
        if (ticketCarMap.containsKey(ticket)) {
            return true;
        }
        return false;
    }


}
