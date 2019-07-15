package com.thoughtworks.tdd;

import com.thoughtworks.tdd.excception.TicketMissingException;
import com.thoughtworks.tdd.excception.UnrecognizedTicketException;

import java.util.Arrays;
import java.util.List;

public abstract class Parker implements Parkable {

    protected List<ParkingLot> parkingLots;

    public Parker(ParkingLot... parkingLots){
        this.parkingLots = Arrays.asList(parkingLots);
    }

    @Override
    public boolean isFull() {
        return parkingLots.stream().allMatch(parkingLot -> parkingLot.isFull());
    }

    @Override
    public boolean isContainTicket(Ticket ticket) {
        return parkingLots.stream().anyMatch(parkingLot -> parkingLot.isContainTicket(ticket));
    }

    @Override
    public Car fetch(Ticket ticket) {
        if (ticket == null) {
            throw new TicketMissingException();
        }
        boolean isContainTicket = parkingLots.stream().anyMatch(parkingLot -> parkingLot.isContainTicket(ticket));
        if (isContainTicket) {
            ParkingLot firstAvailableParkingLot = parkingLots.stream().filter(parkingLot -> parkingLot.isContainTicket(ticket)).findFirst().get();
            return firstAvailableParkingLot.fetch(ticket);
        }
        throw new UnrecognizedTicketException();
    }

}
