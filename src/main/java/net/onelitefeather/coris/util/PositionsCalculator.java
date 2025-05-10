package net.onelitefeather.coris.util;

import net.minestom.server.coordinate.Point;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Set;

/**
 * A functional interface for calculating positions between two points.
 *
 * @param <T> the type of point
 * @version 1.0.0
 * @since 0.1.0
 * @author theEvilReaper
 */
@FunctionalInterface
public interface PositionsCalculator<T extends Point> {

    /**
     * Calculates the positions between two points.
     * @return the positions between the two points
     */
    @NotNull
    @UnmodifiableView
    Set<T> calculatePositions(@NotNull @UnmodifiableView List<T> points);

}
