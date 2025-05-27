package net.onelitefeather.coris.shape;

import net.minestom.server.coordinate.Vec;
import org.jetbrains.annotations.NotNull;

/**
 * The {@link PointShape} represents an implementation of the {@link Shape} interface which describes a single point in a 3D space.
 * It is used to represent a point in the world and can be used to check if a point intersects with the shape.
 *
 * @param position the position of the point in the 3D space
 * @author theEvilReaper
 * @version 1.0.0
 * @since 0.1.0
 */
public record PointShape(@NotNull Vec position) implements Shape<Vec> {

    @Override
    public int compareTo(@NotNull Shape<Vec> o) {
        if (!(o instanceof PointShape(Vec position1))) return -1;
        int cmpX = Double.compare(this.position.x(), position1.x());
        if (cmpX != 0) return cmpX;
        int cmpY = Double.compare(this.position.y(), position1.y());
        if (cmpY != 0) return cmpY;
        return Double.compare(this.position.z(), position1.z());
    }

    @Override
    public boolean intersect2D(@NotNull Vec position) {
        return this.position.x() == position.x() &&
               this.position.y() == position.y();
    }

    @Override
    public boolean intersect3D(@NotNull Vec position) {
        return this.position.x() == position.x() &&
               this.position.y() == position.y() &&
               this.position.z() == position.z();
    }
}
