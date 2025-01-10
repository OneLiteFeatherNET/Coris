package net.theevilreaper.coris.shape;

import net.minestom.server.coordinate.Point;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The base shape class provides a basic implementation of the {@link Shape} interface.
 *
 * @param <T> the {@link Point} type of the shape
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class BaseShape<T extends Point> implements Shape<T> {

    protected final T start;
    protected final T end;
    protected final Set<T> positions;

    /**
     * Creates a new shape with the given start and end point.
     *
     * @param start the start point
     * @param end   the end point
     */
    BaseShape(@NotNull T start, @NotNull T end) {
        this.start = start;
        this.end = end;
        this.positions = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BaseShape<?> baseShape = (BaseShape<?>) o;
        return Objects.equals(start, baseShape.start) && Objects.equals(end, baseShape.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    /**
     * Compares the current shape with the given object.
     *
     * @param o the object to be compared.
     * @return the comparison result
     */
    @Override
    public int compareTo(@NotNull Shape<T> o) {
        return Integer.compare(this.hashCode(), o.hashCode());
    }

    /**
     * Returns the start point of the shape.
     *
     * @return the start point
     */
    @Override
    public @NotNull T start() {
        return this.start;
    }

    /**
     * Returns the end point of the shape.
     *
     * @return the end point
     */
    @Override
    public @NotNull T end() {
        return this.end;
    }

    @Override
    public @NotNull @UnmodifiableView Set<T> positions() {
        return Collections.unmodifiableSet(this.positions);
    }
}
