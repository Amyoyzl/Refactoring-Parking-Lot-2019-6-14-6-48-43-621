package com.thoughtworks.tdd;

import com.thoughtworks.tdd.excception.NotEnoughPositionException;
import com.thoughtworks.tdd.excception.TicketMissingException;
import com.thoughtworks.tdd.excception.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingBoyTest {

    @Test
    public void should_return_car_when_parkingBoy_fetch_car_given_parkingTicket() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        Ticket ticket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(ticket);

        assertSame(fetchCar, car);
    }

    @Test
    public void should_return_cars_when_parkingBoy_fetch_cars_given_parkingTickets() {
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car1 = new Car();
        Car car2 = new Car();

        Ticket parkingTicket1 = parkingBoy.park(car1);
        Ticket parkingTicket2 = parkingBoy.park(car2);
        Car fetchCar1 = parkingBoy.fetch(parkingTicket1);
        Car fetchCar2 = parkingBoy.fetch(parkingTicket2);

        assertSame(fetchCar1, car1);
        assertSame(fetchCar2, car2);
    }

    @Test
    public void should_return_no_car_when_parkingBoy_fetch_car_given_wrong_parkingTicket() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Ticket ticket = new Ticket();

        UnrecognizedTicketException unrecognizedTicketException = new UnrecognizedTicketException();
        assertThrows(unrecognizedTicketException.getClass(), () -> parkingBoy.fetch(ticket));
        assertSame(unrecognizedTicketException.getMessage(), "Unrecognized parking ticket.");
    }

    @Test
    public void should_return_no_car_when_parkingBoy_fetch_car_given_used_parkingTicket() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Ticket ticket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(ticket);

        assertSame(fetchCar, car);
        UnrecognizedTicketException unrecognizedTicketException = new UnrecognizedTicketException();
        assertThrows(unrecognizedTicketException.getClass(), () -> parkingBoy.fetch(ticket));
        assertSame(unrecognizedTicketException.getMessage(), "Unrecognized parking ticket.");
    }

    @Test
    public void should_return_no_parkingTicket_when_parkingBoy_park_car_has_no_position_in_parkingLot() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.park(car);

        NotEnoughPositionException notEnoughPositionException = new NotEnoughPositionException();
        assertThrows(notEnoughPositionException.getClass(), () -> parkingBoy.park(car));
        assertSame(notEnoughPositionException.getMessage(), "Not enough position.");
    }

    @Test
    public void should_provide_ticket_message_when_parkingBoy_fetch_car_given_no_parkingTicket() {
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        TicketMissingException ticketMissingException = new TicketMissingException();
        assertThrows(ticketMissingException.getClass(), () -> parkingBoy.fetch(null));
        assertSame(new TicketMissingException().getMessage(), "Please provide your parking ticket.");
    }

    @Test
    public void should_park_and_fetch_cars_in_parkingLots() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot1, parkingLot2);
        Car car1 = new Car();
        Car car2 = new Car();

        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);
        Car fetchCar1 = parkingBoy.fetch(ticket1);
        Car fetchCar2 = parkingBoy.fetch(ticket2);

        assertSame(fetchCar1, car1);
        assertSame(fetchCar2, car2);
    }

    @Test
    public void should_smart_parkingBoy_park_and_fetch_car_to_more_empty_position() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot1, parkingLot2);
        Car car1 = new Car();
        Car car2 = new Car();

        Ticket ticket1 = smartParkingBoy.park(car1);
        Ticket ticket2 = smartParkingBoy.park(car2);
        Car fetchCar1 = smartParkingBoy.fetch(ticket1);
        Car fetchCar2 = smartParkingBoy.fetch(ticket2);

        assertSame(fetchCar1, car1);
        assertSame(fetchCar2, car2);
    }

    @Test
    public void should_provide_ticket_message_when_SmartParkingBoy_fetch_car_given_no_parkingTicket() {
        ParkingLot parkingLot = new ParkingLot(1);
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLot);

        TicketMissingException ticketMissingException = new TicketMissingException();
        assertThrows(ticketMissingException.getClass(), () -> parkingBoy.fetch(null));
        assertSame(new TicketMissingException().getMessage(), "Please provide your parking ticket.");
    }

    @Test
    public void should_super_smart_parkingBoy_park_and_fetch_car_to_more_empty_position() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        SuperSmartParkingBoy smartParkingBoy = new SuperSmartParkingBoy(parkingLot1, parkingLot2);
        Car car1 = new Car();
        Car car2 = new Car();

        Ticket ticket1 = smartParkingBoy.park(car1);
        Ticket ticket2 = smartParkingBoy.park(car2);
        Car fetchCar1 = smartParkingBoy.fetch(ticket1);
        Car fetchCar2 = smartParkingBoy.fetch(ticket2);

        assertSame(fetchCar1, car1);
        assertSame(fetchCar2, car2);
    }


}
