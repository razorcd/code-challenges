package com.other.challenges.airplaneseats;

import org.junit.Test;

import static com.other.challenges.airplaneseats.SeatType.FREE;
import static com.other.challenges.airplaneseats.SeatType.NOSEAT;
import static com.other.challenges.airplaneseats.SeatType.OCCUPIED;
import static org.junit.Assert.*;

public class AirplaneSeatsTest {

    @Test
    public void initializedAirplaneSeatsShouldReturnCorrectEmptySeats() {
        AirplaneSeats airplaneSeats = new AirplaneSeats(new SeatType[]{FREE, NOSEAT, FREE}, 2);
        assertArrayEquals("Should return initialized empty seats.",
                new SeatType[][]{
                        {FREE, NOSEAT, FREE},
                        {FREE, NOSEAT, FREE}},
                airplaneSeats.getSeatTypes());
    }

    @Test
    public void addsFirst4Passengers() {
        AirplaneSeats airplaneSeats = new AirplaneSeats(new SeatType[]{FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE}, 3);
        airplaneSeats.addPassengers(4);
        assertArrayEquals("Should return occupied first middle column",
                new SeatType[][]{
                        {FREE, FREE, NOSEAT, OCCUPIED, OCCUPIED, OCCUPIED, OCCUPIED, NOSEAT, FREE, FREE},
                        {FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE},
                        {FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE}
                }
                , airplaneSeats.getSeatTypes());
    }

    @Test
    public void adds2GroupsOf4Passengers() {
        AirplaneSeats airplaneSeats = new AirplaneSeats(new SeatType[]{FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE}, 3);
        airplaneSeats.addPassengers(4);
        airplaneSeats.addPassengers(4);
        assertArrayEquals("Should return occupied first and second middle column",
                new SeatType[][]{
                        {FREE, FREE, NOSEAT, OCCUPIED, OCCUPIED, OCCUPIED, OCCUPIED, NOSEAT, FREE, FREE},
                        {FREE, FREE, NOSEAT, OCCUPIED, OCCUPIED, OCCUPIED, OCCUPIED, NOSEAT, FREE, FREE},
                        {FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE}
                }
                , airplaneSeats.getSeatTypes());
    }

    @Test(expected = IllegalStateException.class)
    public void addsGroupsThatDoNotFit() {
        AirplaneSeats airplaneSeats = new AirplaneSeats(new SeatType[]{FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE}, 3);
        airplaneSeats.addPassengers(5);
    }


    @Test
    public void adds1GroupOf3Passengers() {
        AirplaneSeats airplaneSeats = new AirplaneSeats(new SeatType[]{FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE}, 3);
        airplaneSeats.addPassengers(3);
        assertArrayEquals("Should return occupied first 3 seats of middle column",
                new SeatType[][]{
                        {FREE, FREE, NOSEAT, OCCUPIED, OCCUPIED, OCCUPIED, FREE, NOSEAT, FREE, FREE},
                        {FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE},
                        {FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE}
                }
                , airplaneSeats.getSeatTypes());
    }

    @Test
    public void adds1GroupOf2Passengers() {
        AirplaneSeats airplaneSeats = new AirplaneSeats(new SeatType[]{FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE}, 3);
        airplaneSeats.addPassengers(2);
        assertArrayEquals("Should return occupied first 2 seats of first column",
                new SeatType[][]{
                        {OCCUPIED, OCCUPIED, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE},
                        {FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE},
                        {FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE}
                }
                , airplaneSeats.getSeatTypes());
    }

    @Test
    public void adds1GroupOf1Passager() {
        AirplaneSeats airplaneSeats = new AirplaneSeats(new SeatType[]{FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE}, 3);
        airplaneSeats.addPassengers(1);
        assertArrayEquals("Should return occupied first seat of first column",
                new SeatType[][]{
                        {OCCUPIED, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE},
                        {FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE},
                        {FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE}
                }
                , airplaneSeats.getSeatTypes());
    }

    @Test
    public void adds4RandomGroupsOfPassengers() {
        AirplaneSeats airplaneSeats = new AirplaneSeats(new SeatType[]{FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE}, 3);
        airplaneSeats.addPassengers(2);
        airplaneSeats.addPassengers(3);
        airplaneSeats.addPassengers(2);
        airplaneSeats.addPassengers(1);
        assertArrayEquals("Should return occupied first full row.",
                new SeatType[][]{
                        {OCCUPIED, OCCUPIED, NOSEAT, OCCUPIED, OCCUPIED, OCCUPIED, OCCUPIED, NOSEAT, OCCUPIED, OCCUPIED},
                        {FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE},
                        {FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE}
                }
                , airplaneSeats.getSeatTypes());
    }

    @Test
    public void adds3RandomGroupsOfPassengers() {
        AirplaneSeats airplaneSeats = new AirplaneSeats(new SeatType[]{FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE}, 3);
        airplaneSeats.addPassengers(3);
        airplaneSeats.addPassengers(1);
        airplaneSeats.addPassengers(2);
        airplaneSeats.addPassengers(4);
        airplaneSeats.addPassengers(1);
        airplaneSeats.addPassengers(4);
        airplaneSeats.addPassengers(2);
        assertArrayEquals("Should return occupied seats based on some random passenger groups.",
                new SeatType[][]{
                        {OCCUPIED, OCCUPIED, NOSEAT, OCCUPIED, OCCUPIED, OCCUPIED, FREE, NOSEAT, OCCUPIED, OCCUPIED},
                        {OCCUPIED, OCCUPIED, NOSEAT, OCCUPIED, OCCUPIED, OCCUPIED, OCCUPIED, NOSEAT, FREE, FREE},
                        {FREE, FREE, NOSEAT, OCCUPIED, OCCUPIED, OCCUPIED, OCCUPIED, NOSEAT, FREE, FREE}
                }
                , airplaneSeats.getSeatTypes());
    }

//    TODO: try first 4 seats. else try 2 of 2 teasts. Needs Transaction implementation.
    @Test
    public void adds4GroupsOf4PassengersShouldSplitLastGroup() {
        AirplaneSeats airplaneSeats = new AirplaneSeats(new SeatType[]{FREE, FREE, NOSEAT, FREE, FREE, FREE, FREE, NOSEAT, FREE, FREE}, 3);
        airplaneSeats.addPassengers(4);
        airplaneSeats.addPassengers(4);
        airplaneSeats.addPassengers(4);
        airplaneSeats.addPassengers(4);
        assertArrayEquals("Should return occupied first, second and third middle column and first 2 rows of first column.",
                new SeatType[][]{
                        {OCCUPIED, OCCUPIED, NOSEAT, OCCUPIED, OCCUPIED, OCCUPIED, OCCUPIED, NOSEAT, FREE, FREE},
                        {OCCUPIED, OCCUPIED, NOSEAT, OCCUPIED, OCCUPIED, OCCUPIED, OCCUPIED, NOSEAT, FREE, FREE},
                        {FREE, FREE, NOSEAT, OCCUPIED, OCCUPIED, OCCUPIED, OCCUPIED, NOSEAT, FREE, FREE},
                }
                , airplaneSeats.getSeatTypes());
    }
}