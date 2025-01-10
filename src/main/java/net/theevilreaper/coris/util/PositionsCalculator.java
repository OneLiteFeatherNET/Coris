package net.theevilreaper.coris.util;

import net.minestom.server.coordinate.Point;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Set;

@FunctionalInterface
public interface PositionsCalculator<T extends Point> {

    @NotNull
    @UnmodifiableView
    Set<T> calculatePositions(@NotNull T start, @NotNull T end);

}
