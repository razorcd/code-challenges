public class MarsRover {

    private final int boardX;
    private final int boardY;
    private int currentX;
    private int currentY;
    private Character facing;

    /**
     * Defines the board with a rover.
     *
     * @param boardX x size of the board
     * @param boardY y size of the board
     */
    public MarsRover(final int boardX, final int boardY) {
        this.boardX = boardX;
        this.boardY = boardY;
    }

    /**
     * Performs action on rover.
     * @param action actions to perform: M - performActions forward 1 point, L - turn left, R - turn right
     */
    public void performActions(final String action) {
        for (Character c : action.toCharArray()) {
            performOneAction(c);
        }
    }

    /**
     * Get current position of the rover
     *
     * @return [String] format: x y orientation. e.g: 1 3 N
     */
    public String getCurrentPosition() {
        return currentX + " " + currentY + " " + facing;
    }

    /**
     * Set current position of the rover.
     *
     * @param currentX x
     * @param currentY y
     * @param facing orientation
     */
    public void setCurrentPosition(final int currentX, final int currentY, final char facing) {
        this.currentX = currentX;
        this.currentY = currentY;
        this.facing = facing;
    }



    private void performOneAction(final Character action) {
        if (action.equals('M')) moveOnce();
        else if (action.equals('L')) turnLeft();
        else if (action.equals('R')) turnRight();
    }

    private void moveOnce() {
        if (facing.equals('N')) { currentY++; return; }
        if (facing.equals('E')) { currentX++; return; }
        if (facing.equals('S')) { currentY--; return; }
        if (facing.equals('W')) { currentX--; return; }
    }

    private void turnLeft() {
        if (facing.equals('N')) { facing = 'W'; return; }
        if (facing.equals('E')) { facing = 'N'; return; }
        if (facing.equals('S')) { facing = 'E'; return; }
        if (facing.equals('W')) { facing = 'S'; return; }
    }

    private void turnRight() {
        if (facing.equals('N')) { facing = 'E'; return; }
        if (facing.equals('E')) { facing = 'S'; return; }
        if (facing.equals('S')) { facing = 'W'; return; }
        if (facing.equals('W')) { facing = 'N'; return; }
    }
}
