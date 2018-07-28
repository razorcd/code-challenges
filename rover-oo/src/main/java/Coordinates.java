import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

public class Coordinates {
    private final int x;
    private final int y;

    // defines methods to calculate next coordinate after movement towards Orientation
    private static final Map<Orientation, BiFunction<Integer, Integer, Coordinates>> operation;
    static {
        operation = new EnumMap<>(Orientation.class);
        operation.put(Orientation.N, (x, y) -> new Coordinates(x, y + 1));
        operation.put(Orientation.E, (x, y) -> new Coordinates(x + 1, y));
        operation.put(Orientation.S, (x, y) -> new Coordinates(x, y - 1));
        operation.put(Orientation.W, (x, y) -> new Coordinates(x - 1, y));
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get new coordinates by moving one step towards specified direction.
     *
     * @param direction compass direction
     * @return new Coordinates after movement
     */
    public Coordinates coordinatesTowards(Orientation direction) {
        return operation.get(direction).apply(x,y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    /**
     * Checks if current coordinate is withing the two specified coordinates boundary.
     *
     * @param lowBoundary low boundary coordinates
     * @param highBoundary high boundary coordinates
     * @return boolean
     */
    public boolean isWithin(Coordinates lowBoundary , Coordinates highBoundary) {
        return lowBoundary.x <= x && lowBoundary.y <= y &&
               highBoundary.x >= x && highBoundary.y >= y;
    }
}
