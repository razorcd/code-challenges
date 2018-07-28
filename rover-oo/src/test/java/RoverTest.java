import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RoverTest {
   @Test
   public void testInitializeMarsRover() {
       Coordinates plateauCoordinates = new Coordinates(5,5);
       Coordinates roverCoordinates = new Coordinates(1,1);
       Rover marsRover = new Rover(plateauCoordinates, roverCoordinates, Orientation.N);

       String coordinates = marsRover.toString();

       assertEquals("Initialized Rover should return rover coordinates", "1 1 N", coordinates);
   }

   @Test
   public void testMoveWhenRoverFacesNorth() {
       Coordinates plateauCoordinates = new Coordinates(5,5);
       Coordinates roverCoordinates = new Coordinates(1,1);
       Rover marsRover = new Rover(plateauCoordinates, roverCoordinates, Orientation.N);

       marsRover.moveOnce();

       assertEquals("Rover should move one grid up when facing N", "1 2 N", marsRover.toString());
   }

    @Test
    public void testMoveWhenRoverFacesEast() {
        Coordinates plateauCoordinates = new Coordinates(5,5);
        Coordinates roverCoordinates = new Coordinates(1,1);
        Rover marsRover = new Rover(plateauCoordinates, roverCoordinates, Orientation.E);

        marsRover.moveOnce();

        assertEquals("Rover should move one grid up when facing E", "2 1 E", marsRover.toString());
    }

    @Test
    public void testMoveWhenRoverFacesSouth() {
        Coordinates plateauCoordinates = new Coordinates(5,5);
        Coordinates roverCoordinates = new Coordinates(1,1);
        Rover marsRover = new Rover(plateauCoordinates, roverCoordinates, Orientation.S);

        marsRover.moveOnce();

        assertEquals("Rover should move one grid up when facing S", "1 0 S", marsRover.toString());
    }

    @Test
    public void testMoveWhenRoverFacesWest() {
        Coordinates plateauCoordinates = new Coordinates(5,5);
        Coordinates roverCoordinates = new Coordinates(1,1);
        Rover marsRover = new Rover(plateauCoordinates, roverCoordinates, Orientation.W);

        marsRover.moveOnce();

        assertEquals("Rover should move one grid up when facing W", "0 1 W", marsRover.toString());
    }



    @Test
    public void testTurnLeftWhenRoverFacesNorth() {
        Coordinates plateauCoordinates = new Coordinates(5,5);
        Coordinates roverCoordinates = new Coordinates(1,1);
        Rover marsRover = new Rover(plateauCoordinates, roverCoordinates, Orientation.N);

        marsRover.turnLeft();

        assertEquals("Rover should turn LEFT when facing N", "1 1 W", marsRover.toString());
    }

    @Test
    public void testTurnLeftWhenRoverFacesEast() {
        Coordinates plateauCoordinates = new Coordinates(5,5);
        Coordinates roverCoordinates = new Coordinates(1,1);
        Rover marsRover = new Rover(plateauCoordinates, roverCoordinates, Orientation.E);

        marsRover.turnLeft();

        assertEquals("Rover should turn LEFT when facing E", "1 1 N", marsRover.toString());
    }

    @Test
    public void testTurnLeftWhenRoverFacesSouth() {
        Coordinates plateauCoordinates = new Coordinates(5,5);
        Coordinates roverCoordinates = new Coordinates(1,1);
        Rover marsRover = new Rover(plateauCoordinates, roverCoordinates, Orientation.S);

        marsRover.turnLeft();

        assertEquals("Rover should turn LEFT when facing S", "1 1 E", marsRover.toString());
    }

    @Test
    public void testTurnLeftWhenRoverFacesWest() {
        Coordinates plateauCoordinates = new Coordinates(5,5);
        Coordinates roverCoordinates = new Coordinates(1,1);
        Rover marsRover = new Rover(plateauCoordinates, roverCoordinates, Orientation.W);

        marsRover.turnLeft();

        assertEquals("Rover should turn LEFT when facing W", "1 1 S", marsRover.toString());
    }




    @Test
    public void testTurnRightWhenRoverFacesNorth() {
        Coordinates plateauCoordinates = new Coordinates(5,5);
        Coordinates roverCoordinates = new Coordinates(1,1);
        Rover marsRover = new Rover(plateauCoordinates, roverCoordinates, Orientation.N);

        marsRover.turnRight();

        assertEquals("Rover should turn RIGHT when facing N", "1 1 E", marsRover.toString());
    }

    @Test
    public void testTurnRightWhenRoverFacesEast() {
        Coordinates plateauCoordinates = new Coordinates(5,5);
        Coordinates roverCoordinates = new Coordinates(1,1);
        Rover marsRover = new Rover(plateauCoordinates, roverCoordinates, Orientation.E);

        marsRover.turnRight();

        assertEquals("Rover should turn RIGHT when facing E", "1 1 S", marsRover.toString());
    }

    @Test
    public void testTurnRightWhenRoverFacesSouth() {
        Coordinates plateauCoordinates = new Coordinates(5,5);
        Coordinates roverCoordinates = new Coordinates(1,1);
        Rover marsRover = new Rover(plateauCoordinates, roverCoordinates, Orientation.S);

        marsRover.turnRight();

        assertEquals("Rover should turn RIGHT when facing S", "1 1 W", marsRover.toString());
    }

    @Test
    public void testTurnRightWhenRoverFacesWest() {
        Coordinates plateauCoordinates = new Coordinates(5,5);
        Coordinates roverCoordinates = new Coordinates(1,1);
        Rover marsRover = new Rover(plateauCoordinates, roverCoordinates, Orientation.W);

        marsRover.turnRight();

        assertEquals("Rover should turn RIGHT when facing W", "1 1 N", marsRover.toString());
    }

    @Test
    public void testToString() {
        Coordinates plateauCoordinates = new Coordinates(10,10);
        Coordinates roverCoordinates = new Coordinates(5,6);
        Rover marsRover = new Rover(plateauCoordinates, roverCoordinates, Orientation.W);

        assertEquals("toString method of Rover should return correct data.", "5 6 W", marsRover.toString());
    }



    @Test(expected = RuntimeException.class)
    public void testShouldNOTMoveBeyodPlateauBoundariesNorth() {
        Coordinates plateauCoordinates = new Coordinates(10,10);
        Coordinates roverCoordinates = new Coordinates(5,10);
        Rover marsRover = new Rover(plateauCoordinates, roverCoordinates, Orientation.N);

        marsRover.moveOnce();

        assertNotEquals("Should not move N outside of plateau boundaries.", "5 11 N", marsRover.toString());
    }

    @Test(expected = RuntimeException.class)
    public void testShouldNOTMoveBeyodPlateauBoundariesEast() {
        Coordinates plateauCoordinates = new Coordinates(10,10);
        Coordinates roverCoordinates = new Coordinates(10,5);
        Rover marsRover = new Rover(plateauCoordinates, roverCoordinates, Orientation.E);

        marsRover.moveOnce();

        assertNotEquals("Should not move E outside of plateau boundaries.", "11 5 E", marsRover.toString());
    }

    @Test(expected = RuntimeException.class)
    public void testShouldNOTMoveBeyodPlateauBoundariesSouth() {
        Coordinates plateauCoordinates = new Coordinates(10,10);
        Coordinates roverCoordinates = new Coordinates(5,0);
        Rover marsRover = new Rover(plateauCoordinates, roverCoordinates, Orientation.S);

        marsRover.moveOnce();

        assertNotEquals("Should not move S outside of plateau boundaries.", "5 -1 S", marsRover.toString());
    }

    @Test(expected = RuntimeException.class)
    public void testShouldNOTMoveBeyodPlateauBoundariesWest() {
        Coordinates plateauCoordinates = new Coordinates(10,10);
        Coordinates roverCoordinates = new Coordinates(0,5);
        Rover marsRover = new Rover(plateauCoordinates, roverCoordinates, Orientation.W);

        marsRover.moveOnce();

        assertNotEquals("Should not move W outside of plateau boundaries.", "-1 5 W", marsRover.toString());
    }
}


//
// Domains:
//  - Plateau   (needed for rover coordinates validation)
//    - boundary Coordianates
//  - Rover
//      - Coordinates
//      - Orientation
//
//  - Input strings
//
// Behaviors:
//    - define Plateau boundaries
//    - set initial Rover position
//    - set initial Rover orientation(N,S,E,W)
//    - set Rover movements (*)
//    - get Rover position
//    - validate next move coordinates within Plateau boundaries
//
