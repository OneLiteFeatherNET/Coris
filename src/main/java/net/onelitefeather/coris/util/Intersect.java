package net.onelitefeather.coris.util;

import net.minestom.server.coordinate.Point;

/**
 * Provides intersection checks between a geometric shape and a point.
 * <p>
 * Implementations define how intersection is evaluated.
 * This may represent 2D, 3D or hybrid spatial logic depending on the shape.
 * <p>
 * The caller MUST NOT assume any specific dimensional model.
 *
 * @param <T> the type of point
 * @author theEvilReaper
 * @version 1.2.0
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
