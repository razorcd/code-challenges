import org.junit.Test;
import static org.junit.Assert.*;

public class MarsRoverTest {

    @Test
    public void shouldInitializePosition() {
        MarsRover marsRover = new MarsRover(5,5);
        marsRover.setCurrentPosition(1, 2, 'N');
        assertEquals("Should initialize position.", "1 2 N", marsRover.getCurrentPosition());
    }


   @Test
   public void shouldMoveOnePositionForwardWhenFacingNorth() {
       MarsRover marsRover = new MarsRover(5,5);
       marsRover.setCurrentPosition(1, 2, 'N');
       marsRover.performActions("M");
       assertEquals("Should performActions one position forward when facing N.", "1 3 N", marsRover.getCurrentPosition());
   }

    @Test
    public void shouldMoveOnePositionForwardWhenFacingEast() {
        MarsRover marsRover = new MarsRover(5,5);
        marsRover.setCurrentPosition(1, 2, 'E');
        marsRover.performActions("M");
        assertEquals("Should performActions one position forward when facing E.", "2 2 E", marsRover.getCurrentPosition());
    }

    @Test
    public void shouldMoveOnePositionForwardWhenFacingSouth() {
        MarsRover marsRover = new MarsRover(5,5);
        marsRover.setCurrentPosition(1, 2, 'S');
        marsRover.performActions("M");
        assertEquals("Should performActions one position forward when facing S.", "1 1 S", marsRover.getCurrentPosition());
    }

    @Test
    public void shouldMoveOnePositionForwardWhenFacingWest() {
        MarsRover marsRover = new MarsRover(5,5);
        marsRover.setCurrentPosition(1, 2, 'W');
        marsRover.performActions("M");
        assertEquals("Should performActions one position forward when facing W.", "0 2 W", marsRover.getCurrentPosition());
    }



    @Test
    public void shouldTurnOrientationLeftWhenFacingNorth() {
        MarsRover marsRover = new MarsRover(5,5);
        marsRover.setCurrentPosition(1, 2, 'N');
        marsRover.performActions("L");
        assertEquals("Should turn orientation LEFT when facing N.", "1 2 W", marsRover.getCurrentPosition());
    }

    @Test
    public void shouldTurnOrientationLeftWhenFacingEast() {
        MarsRover marsRover = new MarsRover(5,5);
        marsRover.setCurrentPosition(1, 2, 'E');
        marsRover.performActions("L");
        assertEquals("Should turn orientation LEFT when facing E.", "1 2 N", marsRover.getCurrentPosition());
    }

    @Test
    public void shouldTurnOrientationLeftWhenFacingSouth() {
        MarsRover marsRover = new MarsRover(5,5);
        marsRover.setCurrentPosition(1, 2, 'S');
        marsRover.performActions("L");
        assertEquals("Should turn orientation LEFT when facing S.", "1 2 E", marsRover.getCurrentPosition());
    }

    @Test
    public void shouldTurnOrientationLeftWhenFacingWest() {
        MarsRover marsRover = new MarsRover(5,5);
        marsRover.setCurrentPosition(1, 2, 'W');
        marsRover.performActions("L");
        assertEquals("Should turn orientation LEFT when facing N.", "1 2 S", marsRover.getCurrentPosition());
    }



    @Test
    public void shouldTurnOrientationRightWhenFacingNorth() {
        MarsRover marsRover = new MarsRover(5,5);
        marsRover.setCurrentPosition(1, 2, 'N');
        marsRover.performActions("R");
        assertEquals("Should turn orientation RIGHT when facing N.", "1 2 E", marsRover.getCurrentPosition());
    }

    @Test
    public void shouldTurnOrientationrightWhenFacingEast() {
        MarsRover marsRover = new MarsRover(5,5);
        marsRover.setCurrentPosition(1, 2, 'E');
        marsRover.performActions("R");
        assertEquals("Should turn orientation RIGHT when facing E.", "1 2 S", marsRover.getCurrentPosition());
    }

    @Test
    public void shouldTurnOrientationRightWhenFacingSouth() {
        MarsRover marsRover = new MarsRover(5,5);
        marsRover.setCurrentPosition(1, 2, 'S');
        marsRover.performActions("R");
        assertEquals("Should turn orientation RIGHT when facing S.", "1 2 W", marsRover.getCurrentPosition());
    }

    @Test
    public void shouldTurnOrientationRightWhenFacingWest() {
        MarsRover marsRover = new MarsRover(5, 5);
        marsRover.setCurrentPosition(1, 2, 'W');
        marsRover.performActions("R");
        assertEquals("Should turn orientation RIGHT when facing N.", "1 2 N", marsRover.getCurrentPosition());
    }


    @Test
    public void shouldPerformActions1() {
        MarsRover marsRover = new MarsRover(5,5);
        marsRover.setCurrentPosition(1, 2, 'N');
        marsRover.performActions("LMLMLMLMM");
        assertEquals("Should perform LMLMLMLMM actions.", "1 3 N", marsRover.getCurrentPosition());
    }

    @Test
    public void shouldPerformActions2() {
        MarsRover marsRover = new MarsRover(5,5);
        marsRover.setCurrentPosition(3, 3, 'E');
        marsRover.performActions("MMRMMRMRRM");
        assertEquals("Should perform MMRMMRMRRM actions.", "5 1 E", marsRover.getCurrentPosition());
    }

    @Test
    public void shouldPerformMultipleActions() {
        MarsRover marsRover = new MarsRover(5,5);
        marsRover.setCurrentPosition(1, 2, 'N');
        marsRover.performActions("LMLMLMLMM");
        marsRover.setCurrentPosition(3, 3, 'E');
        marsRover.performActions("MMRMMRMRRM");
        assertEquals("Should perform LMLMLMLMM and MMRMMRMRRM actions.", "5 1 E", marsRover.getCurrentPosition());
    }
}
