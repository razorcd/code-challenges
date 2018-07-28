import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinatesTest {

    @Test
    public void testCoordinatesTowardsNorth() {
        Coordinates coordinates = new Coordinates(3, 3);
        Orientation orientation = Orientation.N;

        Coordinates newCoordinates = coordinates.coordinatesTowards(orientation);

        assertEquals("Coordinates towards N should return correct new coordinates", new Coordinates(3,4), newCoordinates);
    }

    @Test
    public void testCoordinatesTowardsEast() {
        Coordinates coordinates = new Coordinates(3, 3);
        Orientation orientation = Orientation.E;

        Coordinates newCoordinates = coordinates.coordinatesTowards(orientation);

        assertEquals("Coordinates towards E should return correct new coordinates", new Coordinates(4,3), newCoordinates);
    }

    @Test
    public void testCoordinatesTowardsSouth() {
        Coordinates coordinates = new Coordinates(3, 3);
        Orientation orientation = Orientation.S;

        Coordinates newCoordinates = coordinates.coordinatesTowards(orientation);

        assertEquals("Coordinates towards S should return correct new coordinates", new Coordinates(3,2), newCoordinates);
    }

    @Test
    public void testCoordinatesTowardsWest() {
        Coordinates coordinates = new Coordinates(3, 3);
        Orientation orientation = Orientation.W;

        Coordinates newCoordinates = coordinates.coordinatesTowards(orientation);

        assertEquals("Coordinates towards W should return correct new coordinates", new Coordinates(2, 3), newCoordinates);
    }

    @Test
    public void testToString() {
        Coordinates coordinates = new Coordinates(3, 5);

        assertEquals("toString coordinates method should return x and y.", "3 5", coordinates.toString());
    }

    @Test
    public void testIsWithingCoordinates() {
        Coordinates coordinates1 = new Coordinates(3, 5);
        Coordinates coordinates2 = new Coordinates(1, 1);
        Coordinates coordinates3 = new Coordinates(3, 2);

        assertTrue("Coordinate (3,2) should be withing coordinate (1,1) and (3,5).", coordinates3.isWithin(coordinates2, coordinates1));
        assertFalse("Coordinate (1,1) should NOT be withing coordinate (3,2) and (3,5).", coordinates2.isWithin(coordinates3, coordinates1));
    }
}