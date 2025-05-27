package net.onelitefeather.coris.shape;

import net.minestom.server.coordinate.Point;
import net.onelitefeather.coris.util.Intersect;
import org.jetbrains.annotations.ApiStatus;

/**
 * The shape interface represents the area of a specific room.
 * It can be used to check if a position is inside the area or not.
 * The shape is a 2D or 3D representation of the area.
 *
 * @param <T> the {@link Point} type of the shape
 * @author theEvilReaper
 * @version 1.0.0
 * @since 0.1.0
 */
@ApiStatus.Experimental
public interface Shape<T extends Point> extends Comparable<Shape<T>>, Intersect<T> { }
