package net.onelitefeather.coris.shape;

import net.minestom.server.coordinate.Point;
import net.onelitefeather.coris.util.Intersect;
import org.jetbrains.annotations.ApiStatus;

/**
 * The shape interface represents the area of a specific room.
 * It can be used to check if a position is inside the area or not.
 * The shape is a 2D or 3D representation of the area.
 *
 * @author theEvilReaper
 * @version 1.3.0
 * @since 0.1.0
 */
@ApiStatus.Experimental
public interface Shape extends Comparable<Shape>, Intersect<Point> {
}
