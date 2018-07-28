public class Rover {
    private final Coordinates plateauBoundary;
    private Coordinates currentCoordinates;
    private Orientation orientation;

    /**
     * Create new Rover with plateau, coordinates and orientation.
     * @param plateauBoundary plateau top right coordinate
     * @param currentCoordinates current coordinates of the rover
     * @param orientation compass orientation
     */
    Rover(Coordinates plateauBoundary, Coordinates currentCoordinates, Orientation orientation) {
        this.plateauBoundary = plateauBoundary;
        this.currentCoordinates = currentCoordinates;
        this.orientation = orientation;
    }

    /**
     * Move rover forward one step.
     */
    public void moveOnce() {
        Coordinates nextCoordinates = currentCoordinates.coordinatesTowards(orientation);
        validatePlateauCoordinatesWithinBoundary(nextCoordinates);   // could be extracted into a Plateau class
        currentCoordinates = nextCoordinates;
    }

    /**
     * Turn rover left.
     */
    public void turnLeft() {
        orientation = orientation.orientLeft();
    }

    /**
     * Turn rover right.
     */
    public void turnRight() {
        orientation = orientation.orientRight();
    }


    /**
     * Ger current position of the Rover
     * @return [String] "x y orientation"
     */
    @Override
    public String toString() {
        return currentCoordinates.toString() + " " + orientation;
    }


    private void validatePlateauCoordinatesWithinBoundary(Coordinates nextCoordinates) {
        if (!nextCoordinates.isWithin(new Coordinates(0,0), plateauBoundary))
            throw new RuntimeException("Can not move outside boundary.");
    }
}
