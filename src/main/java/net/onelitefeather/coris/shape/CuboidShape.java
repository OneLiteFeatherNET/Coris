package net.onelitefeather.coris.shape;

import net.minestom.server.coordinate.Vec;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * The {@link CuboidShape} class represents a 3D cuboid shape defined by two points start and end.
 *
 * @param start the starting point of the cuboid
 * @param end   the ending point of the cuboid
 * @author theEvilReaper
 * @version 1.0.0
 * @since 0.2.0
 */
@ApiStatus.Experimental
public record CuboidShape(@NotNull Vec start, @NotNull Vec end) implements Shape<Vec> {

    /**
     * Creates a new cuboid shape with the specified start and end points.
     *
     * @param start the starting point of the cuboid
     * @param end   the ending point of the cuboid
     * @throws IllegalArgumentException if the distance between start and end is less than or equal to 0
     */
    public CuboidShape {
        double distance = start.distanceSquared(end);

        if (distance <= 0) {
            throw new IllegalArgumentException("The distance between the start and end point must be greater than 0");
        }
    }

    @Override
    public boolean intersect2D(@NotNull Vec position) {
        int x = position.blockX();
        int z = position.blockZ();
        return x >= start.blockX() && x <= end.blockX() && z >= start.blockZ() && z <= end.blockZ();
    }

    @Override
    public boolean intersect3D(@NotNull Vec position) {
        int x = position.blockX();
        int z = position.blockZ();
        int y = position.blockY();
        return x >= start.blockX()
                && x <= end.blockX()
                && y >= start.blockY()
                && y <= end.blockY()
                && z >= start.blockZ()
                && z <= end.blockZ();
    }

    @Override
    public int compareTo(@NotNull Shape<Vec> o) {
        if (!(o instanceof CuboidShape(Vec start1, Vec end1))) {
            return -1;
        }
        int cmpStart = compareVec(this.start, start1);
        if (cmpStart != 0) return cmpStart;
        return compareVec(this.end, end1);
    }

    /**
     * Compares two vectors lexicographically.
     *
     * @param first  the first vector to compare
     * @param second the second vector to compare
     * @return -1 if v1 is less than v2, 0 if they are equal, and 1 if v1 is greater than v2
     */
    private int compareVec(@NotNull Vec first, @NotNull Vec second) {
        int cmpX = Double.compare(first.x(), second.x());
        if (cmpX != 0) return cmpX;

        int cmpY = Double.compare(first.y(), second.y());
        if (cmpY != 0) return cmpY;

        return Double.compare(first.z(), second.z());
    }
}
