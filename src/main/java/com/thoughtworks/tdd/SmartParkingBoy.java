package com.thoughtworks.tdd;

import com.thoughtworks.tdd.excception.NotEnoughPositionException;
import com.thoughtworks.tdd.excception.TicketMissingException;
import com.thoughtworks.tdd.excception.UnrecognizedTicketException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends Parker implements Parkable {

    public SmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        boolean isAllFull = parkingLots.stream().allMatch(parkingLot -> parkingLot.getAvailablePosition() == 0);
        if (isAllFull) {
            throw new NotEnoughPositionException();
        }
        ParkingLot firstAvailableParkingLot = parkingLots.stream().max((o1, o2) ->
                o1.getAvailablePosition() - o2.getAvailablePosition()).get();
        return firstAvailableParkingLot.park(car);
    }

}
