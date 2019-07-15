package com.thoughtworks.tdd;

import com.thoughtworks.tdd.excception.NotEnoughPositionException;
import com.thoughtworks.tdd.excception.TicketMissingException;
import com.thoughtworks.tdd.excception.UnrecognizedTicketException;

import java.util.Arrays;
import java.util.List;

public class ParkingBoy extends Parker implements Parkable {

    public ParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        boolean isAllFull = parkingLots.stream().allMatch(parkingLot -> parkingLot.getAvailablePosition() == 0);
        if (isAllFull) {
            throw new NotEnoughPositionException();
        }
        ParkingLot firstAvailableParkingLot = parkingLots.stream().filter(parkingLot -> parkingLot.getAvailablePosition() > 0).findFirst().get();
        return firstAvailableParkingLot.park(car);
    }

}
