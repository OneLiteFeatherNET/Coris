package net.onelitefeather.coris.util;

import net.minestom.server.coordinate.Point;
import org.jetbrains.annotations.NotNull;

/**
 * The {@link Intersect} interface is used to determine if a point intersects with a shape.
 *
 * @param <T> the type of point
 * @version 1.0.0
 * @since 0.1.0
 */
public interface Intersect<T extends Point> {

    /**
     * Checks if the given point intersects with the shape.
     *
     * @param position the position to check
     * @return true if the point intersects with the shape, false otherwise
     */
    boolean intersect2D(@NotNull T position);

    /**
     * Checks if the given point intersects with the shape in 3D.
     *
     * @param position the position to check
     * @return true if the point intersects with the shape, false otherwise
     */
    boolean intersect3D(@NotNull T position);
}
