package net.onelitefeather.coris;

import net.minestom.server.coordinate.Point;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;

public abstract class PositionBase {

    protected static final Set<Point> EMPTY_LIST = Collections.emptySet();

    public static Set<Point> getEmptyList(@NotNull Point start, @NotNull Point end) {
        return EMPTY_LIST;
    }
}
