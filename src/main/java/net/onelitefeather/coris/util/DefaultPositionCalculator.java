package net.onelitefeather.coris.util;

import net.minestom.server.coordinate.Point;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class DefaultPositionCalculator {

    public static <T extends Point> @NotNull Set<T> getCuboidPoints(@NotNull List<T> dataPoints) {
        Set<T> points = new HashSet<>();

        T start = dataPoints.getFirst();
        T end = dataPoints.getLast();

        points.add(start);

        points.add(end);
        return points;
    }

    private DefaultPositionCalculator() {

    }
}
