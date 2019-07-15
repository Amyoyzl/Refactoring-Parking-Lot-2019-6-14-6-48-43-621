package com.thoughtworks.tdd;

import com.thoughtworks.tdd.excception.NotEnoughPositionException;
import com.thoughtworks.tdd.excception.TicketMissingException;
import com.thoughtworks.tdd.excception.UnrecognizedTicketException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingManager extends Parker {

    private List<Parkable> parkables = new ArrayList<>();

    public ParkingManager(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    public void setParkable(Parkable... parkables) {
        this.parkables = Arrays.asList(parkables);
    }

    @Override
    public Ticket park(Car car) {
        for (Parkable parkable : parkables) {
            if (!parkable.isFull()) {
                return parkable.park(car);
            }
        }
        for (ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.isFull()) {
                return parkingLot.park(car);
            }
        }
        throw new NotEnoughPositionException();
    }

    @Override
    public Car fetch(Ticket ticket) {
        if (ticket == null) {
            throw new TicketMissingException();
        }
        for (Parkable parkable : parkables) {
            if (parkable.isContainTicket(ticket)) {
                return parkable.fetch(ticket);
            }
        }
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.isContainTicket(ticket)) {
                return parkingLot.fetch(ticket);
            }
        }
        throw new UnrecognizedTicketException();
    }
}
