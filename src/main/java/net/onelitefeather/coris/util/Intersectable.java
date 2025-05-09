package net.onelitefeather.coris.util;

import net.minestom.server.coordinate.Point;
import org.jetbrains.annotations.NotNull;

public interface Intersectable<T extends Point> {


    boolean intersect2D(@NotNull T position);

    boolean intersect3D(@NotNull T position);
}
