import org.junit.Test;
import static org.junit.Assert.*;


public class MarsRoverFacadeTest {

    @Test
    public void testMarsRoverMovesMultipleTimes() {
        MarsRoverFacade marsRoverFacade = new MarsRoverFacade(5, 5);
        marsRoverFacade.setRover(1, 2, 'N');

        marsRoverFacade.moveRover("LMLMLMLMM");

        assertEquals("Rover should move LMLMLMLMM.", "1 3 N", marsRoverFacade.toString());
    }

    @Test
    public void testMarsRoverMovesMultipleTimes2() {
        MarsRoverFacade marsRoverFacade = new MarsRoverFacade(5, 5);
        marsRoverFacade.setRover(3, 3, 'E');

        marsRoverFacade.moveRover("MMRMMRMRRM");

        assertEquals("Rover should move MMRMMRMRRM.", "5 1 E", marsRoverFacade.toString());
    }
}

//
//        >> Input:
//        5 5
//        1 2 N
//        LMLMLMLMM
//        3 3 E
//        MMRMMRMRRM
//        >> Expected Output:
//        1 3 N
//        5 1 E