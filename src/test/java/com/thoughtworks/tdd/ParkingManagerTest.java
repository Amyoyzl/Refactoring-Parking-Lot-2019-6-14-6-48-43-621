package com.thoughtworks.tdd;

import com.thoughtworks.tdd.excception.NotEnoughPositionException;
import com.thoughtworks.tdd.excception.TicketMissingException;
import com.thoughtworks.tdd.excception.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingManagerTest {

    @Test
    public void should_return_car_when_manager_make_parkingBoy_fetch_car_given_parkingTicket() {
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingLot parkingLot3 = new ParkingLot(1);
        ParkingLot parkingLot4 = new ParkingLot(1);
        Parker parkingBoy = new ParkingBoy(parkingLot1);
        Parker smartParkingBoy = new SmartParkingBoy(parkingLot2);
        Parker SuperSmartParkingBoy = new SuperSmartParkingBoy(parkingLot3);
        ParkingManager manager = new ParkingManager(parkingLot4);
        manager.setParkable(parkingBoy, smartParkingBoy, SuperSmartParkingBoy);

        Ticket ticket = manager.park(car);
        Car fetchCar = manager.fetch(ticket);

        assertSame(fetchCar, car);

    }

    @Test
    public void should_return_no_position_message_when_parkingBoy_park_car_has_no_position_in_parkingLot() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingLot parkingLot3 = new ParkingLot(1);
        Parker parkingBoy = new ParkingBoy(parkingLot1);
        Parker smartParkingBoy = new SmartParkingBoy(parkingLot2);
        ParkingManager manager = new ParkingManager(parkingLot3);
        manager.setParkable(parkingBoy, smartParkingBoy);

        manager.park(new Car());
        manager.park(new Car());
        manager.park(new Car());

        assertThrows(new NotEnoughPositionException().getClass(), () -> manager.park(new Car()));
        assertSame(new NotEnoughPositionException().getMessage(), ("Not enough position."));
    }

    @Test
    public void should_return_no_car_when_parkingBoy_fetch_car_given_wrong_parkingTicket() {
        ParkingManager manager = new ParkingManager(new ParkingLot(1));
        assertThrows(new UnrecognizedTicketException().getClass(), () -> manager.fetch(new Ticket()));
    }

    @Test
    public void should_return_provide_ticket_when_manager_fetch_car_given_no_parkingTicket() {
        ParkingManager manager = new ParkingManager(new ParkingLot(1));
        assertThrows(new TicketMissingException().getClass(), () -> manager.fetch(null));
        assertSame(new NotEnoughPositionException().getMessage(), ("Not enough position."));
        assertSame(new TicketMissingException().getMessage(),("Please provide your parking ticket."));
    }

}
