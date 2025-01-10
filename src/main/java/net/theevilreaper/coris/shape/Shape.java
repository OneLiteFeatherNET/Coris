package net.theevilreaper.coris.shape;

import net.minestom.server.coordinate.Point;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * The shape interface represents the area of a specific room.
 * It can be used to check if a position is inside the area or not.
 * The shape is a 2D or 3D representation of the area.
 *
 * @param <T> the {@link Point} type of the shape
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 */
@ApiStatus.Experimental
public interface Shape<T extends Point> extends Comparable<Shape<T>> {

    /**
     * Calculates the positions of the shape.
     */
    void calculatePositions();

    /**
     * Calculates the positions of the shape asynchronously.
     */
    @NotNull CompletableFuture<Set<T>> calculatePositionsAsync();

    /**
     * Checks if the given position intersects with the shape in 2D.
     *
     * @param position the position to check
     * @return true if the position intersects with the shape
     */
    boolean intersect2D(@NotNull T position);

    /**
     * Checks if the given position intersects with the shape in 3D.
     *
     * @param position the position to check
     * @return true if the position intersects with the shape
     */
    boolean intersect3D(@NotNull T position);

    /**
     * Returns the relative start point of the shape.
     *
     * @return the start point
     */
    @NotNull T start();

    /**
     * Returns the relative end point of the shape.
     *
     * @return the end point
     */
    @NotNull T end();

    @NotNull
    @UnmodifiableView
    Set<T> positions();

}
