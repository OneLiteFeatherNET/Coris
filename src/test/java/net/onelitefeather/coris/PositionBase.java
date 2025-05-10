package net.onelitefeather.coris;

import net.minestom.server.coordinate.Point;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class PositionBase {

    protected static final Set<Point> EMPTY_LIST = Collections.emptySet();

    public static <T extends Point> Set<T> getEmptyList(@NotNull List<? extends Point> points) {
        return (Set<T>) EMPTY_LIST;
    }
}
