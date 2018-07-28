import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public enum Orientation {

    N, E, S, W;

    private static final Map<Orientation, Orientation> leftOrientations = new EnumMap<>(Orientation.class);
    private static final Map<Orientation, Orientation> rightOrientations = new EnumMap<>(Orientation.class);
    static {
        leftOrientations.put(N, W);
        leftOrientations.put(E, N);
        leftOrientations.put(S, E);
        leftOrientations.put(W, S);

        rightOrientations.put(N, E);
        rightOrientations.put(E, S);
        rightOrientations.put(S, W);
        rightOrientations.put(W, N);
    }

    /**
     * Orients 90 degrees left.
     * @return new orientation
     */
    public Orientation orientLeft() {
        return Objects.requireNonNull(leftOrientations.get(this), "Orient Left operation not supported for orientation " + this.name());
    }

    /**
     * Orients 90 degrees right.
     * @return new orientation
     */
    public Orientation orientRight() {
        return Objects.requireNonNull(rightOrientations.get(this), "Orient Right operation not supported for orientation " + this.name());
    }
}
