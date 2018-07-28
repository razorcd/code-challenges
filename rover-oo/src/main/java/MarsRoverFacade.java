import java.util.Objects;

public class MarsRoverFacade {

    private final Coordinates plateauCoordinates;
    private Rover rover;

    /**
     * Mars Rover Facade to operate with raw inputs
     * @param boundaryX plateau boundary x
     * @param boundaryY plateau boundary y
     */
    MarsRoverFacade(int boundaryX, int boundaryY) {
        this.plateauCoordinates = new Coordinates(boundaryX, boundaryY);
    }

    /**
     * Sets a new rover.
     * @param x coordinate x of rover
     * @param y coordinate y of rover
     * @param orientation compass orientation of rover (N,E,S,W)
     */
    public void setRover(int x, int y, Character orientation) {
        Coordinates roverCoordinates = new Coordinates(x, y);
        rover = new Rover(plateauCoordinates, roverCoordinates, Orientation.valueOf(orientation.toString()));
    }

    /**
     * Performs movements of rover based on a string of characters.
     * @param movements string of movements defined as character. (M-moveOnce, L-turnLeft, R-turnRight)
     */
    public void moveRover(String movements) {
        Objects.requireNonNull(rover, "Rover not defined.");
        for (char m : movements.toCharArray()) {
            if (m=='M') rover.moveOnce();
            else if (m=='L') rover.turnLeft();
            else if (m=='R') rover.turnRight();
        }
    }

    @Override
    public String toString() {
        return rover.toString();
    }
}
