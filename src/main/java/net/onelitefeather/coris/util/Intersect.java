package net.onelitefeather.coris.util;

import net.minestom.server.coordinate.Point;

/**
 * The {@link Intersect} interface is used to determine if a point intersects with a shape.
 *
 * @param <T> the type of point
 * @version 1.1.0
 * @since 0.1.0
 */
public interface Intersect<T extends Point> {

    /**
     * Checks if the given point intersects with the shape.
     *
     * @param position the position to check
     * @return true if the point intersects with the shape, false otherwise
     */
    boolean intersect(T position);

}
