import org.junit.Test;

import static org.junit.Assert.*;

public class OrientationTest {

    @Test
    public void orientLeftFromNorth() {
        Orientation orientation = Orientation.N;

        Orientation newOrientation = orientation.orientLeft();

        assertEquals("Turn LEFT from N orientation should return W.", Orientation.W, newOrientation);
    }

    @Test
    public void orientLeftFromEast() {
        Orientation orientation = Orientation.E;

        Orientation newOrientation = orientation.orientLeft();

        assertEquals("Turn LEFT from E orientation should return N.", Orientation.N, newOrientation);
    }

    @Test
    public void orientLeftFromSouth() {
        Orientation orientation = Orientation.S;

        Orientation newOrientation = orientation.orientLeft();

        assertEquals("Turn LEFT from S orientation should return E.", Orientation.E, newOrientation);
    }

    @Test
    public void orientLeftFromWest() {
        Orientation orientation = Orientation.W;

        Orientation newOrientation = orientation.orientLeft();

        assertEquals("Turn LEFT from W orientation should return S.", Orientation.S, newOrientation);
    }

    @Test
    public void orientRightFromNorth() {
        Orientation orientation = Orientation.N;

        Orientation newOrientation = orientation.orientRight();

        assertEquals("Turn RIGHT from N orientation should return E.", Orientation.E, newOrientation);
    }

    @Test
    public void orientRightFromEast() {
        Orientation orientation = Orientation.E;

        Orientation newOrientation = orientation.orientRight();

        assertEquals("Turn RIGHT from E orientation should return S.", Orientation.S, newOrientation);
    }

    @Test
    public void orientRightFromSouth() {
        Orientation orientation = Orientation.S;

        Orientation newOrientation = orientation.orientRight();

        assertEquals("Turn RIGHT from S orientation should return W.", Orientation.W, newOrientation);
    }

    @Test
    public void orientRightFromWest() {
        Orientation orientation = Orientation.W;

        Orientation newOrientation = orientation.orientRight();

        assertEquals("Turn RIGHT from W orientation should return N.", Orientation.N, newOrientation);
    }
}