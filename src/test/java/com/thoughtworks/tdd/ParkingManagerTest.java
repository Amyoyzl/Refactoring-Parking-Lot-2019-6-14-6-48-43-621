package com.thoughtworks.tdd;

import com.thoughtworks.tdd.excception.NotEnoughPositionException;
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


//    @Test
//    public void should_return_car_when_manager_fetch_car_given_parkingTicket() {
//        Car car = new Car();
//
//        ParkingTicket parkingTicket = manager.parkCar(car);
//        Car fetchCar = manager.fetchCar(parkingTicket);
//
//        assertThat(fetchCar, is(car));
//
//    }

//    @Test
//    public void should_return_cars_when_manager_fetch_cars_given_parkingTickets() {
//        Car car1 = new Car();
//        Car car2 = new Car();
//
//        ParkingTicket parkingTicket1 = manager.parkCar(car1);
//        ParkingTicket parkingTicket2 = manager.parkCar(car2);
//        Car fetchCar1 = manager.fetchCar(parkingTicket1);
//        Car fetchCar2 = manager.fetchCar(parkingTicket2);
//
//        assertThat(fetchCar1, is(car1));
//        assertThat(fetchCar2, is(car2));
//    }

//    @Test
//    public void should_return_no_car_when_manager_fetch_car_given_wrong_parkingTicket() {
//        ParkingTicket parkingTicket = new ParkingTicket(3, 0);
//
//        Car fetchCar = manager.fetchCar(parkingTicket);
//        String parkingMessage = manager.getParkingMessage();
//
//        assertNull(fetchCar);
//        assertThat(parkingMessage, is("Unrecognized parking ticket."));
//    }

//    @Test
//    public void should_return_no_car_when_manager_fetch_car_given_used_parkingTicket() {
//        Car car = new Car();
//
//        ParkingTicket parkingTicket = manager.parkCar(car);
//        Car fetchCar1 = manager.fetchCar(parkingTicket);
//        Car fetchCar2 = manager.fetchCar(parkingTicket);
//        String parkingMessage = manager.getParkingMessage();
//
//        assertThat(fetchCar1, is(car));
//        assertNull(fetchCar2);
//        assertThat(parkingMessage, is("Unrecognized parking ticket."));
//    }

//    @Test
//    public void should_return_no_parkingTicket_when_manager_park_car_has_no_position_in_parkingLot() {
//        for (int i = 0; i < 19; i++) {
//            manager.parkCar(new Car());
//        }
//        Car car20 = new Car();
//        Car car21 = new Car();
//
//        ParkingTicket parkingTicket20 = manager.parkCar(car20);
//        ParkingTicket parkingTicket21 = manager.parkCar(car21);
//        Car fetchCar20 = manager.fetchCar(parkingTicket20);
//
//        assertThat(fetchCar20, is(car20));
//        assertNull(parkingTicket21);
//    }

//    @Test
//    public void should_return_provide_ticket_when_manager_fetch_car_given_no_parkingTicket() {
//        Car fetchCar = manager.fetchCar();
//        String parkingMessage = manager.getParkingMessage();
//
//        assertNull(fetchCar);
//        assertThat(parkingMessage, is("Please provide your parking ticket."));
//    }

//    @Test
//    public void should_return_no_position_message_when_manager_park_car_has_no_position_in_parkingLot() {
//        for (int i = 0; i < 19; i++) {
//            manager.parkCar(new Car());
//        }
//        Car car20 = new Car();
//        Car car21 = new Car();
//
//        ParkingTicket parkingTicket20 = manager.parkCar(car20);
//        ParkingTicket parkingTicket21 = manager.parkCar(car21);
//        String parkingMessage = manager.getParkingMessage();
//        Car fetchCar20 = manager.fetchCar(parkingTicket20);
//
//        assertThat(fetchCar20, is(car20));
//        assertNull(parkingTicket21);
//        assertThat(parkingMessage, is("Not enough position."));
//
//    }

//    @Test
//    public void should_return_cars_when_manager_fetch_cars_in_parkingLots_given_parkingTicket() {
//        for (int i = 0; i < 9; i++) {
//            manager.parkCar(new Car());
//        }
//        Car car10 = new Car();
//        Car car11 = new Car();
//
//        ParkingTicket parkingTicket10 = manager.parkCar(car10);
//        ParkingTicket parkingTicket11 = manager.parkCar(car11);
//        Car fetchCar10 = manager.fetchCar(parkingTicket10);
//        Car fetchCar11 = manager.fetchCar(parkingTicket11);
//
//        assertThat(fetchCar10, is(car10));
//        assertThat(fetchCar11, is(car11));
//    }


}
