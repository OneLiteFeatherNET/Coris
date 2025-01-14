package net.theevilreaper.coris.util;

import net.minestom.server.coordinate.BlockVec;
import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;

public class DefaultPositionCalculator {

    public static <T extends Point> Set<T> calculatePositions(T start, T end) {
        return Set.of();
    }

    private static Set<Vec> caluclateViaVec(@NotNull Vec start, @NotNull Vec end) {
        return Collections.emptySet();
    }

    private static Set<Pos> caluclateViaPos(@NotNull Pos start, @NotNull Pos end) {
        return Collections.emptySet();
    }

    private static Set<BlockVec> calculateViaPoint(@NotNull BlockVec start, @NotNull BlockVec end) {
        return Collections.emptySet();
    }
}
