package net.theevilreaper.coris.objects;

import net.kyori.adventure.key.Key;
import net.minestom.server.coordinate.Vec;
import net.theevilreaper.coris.room.BaseRoom;
import net.theevilreaper.coris.shape.CuboidShape;
import net.theevilreaper.coris.shape.Shape;
import net.theevilreaper.coris.util.DefaultPositionCalculator;
import org.jetbrains.annotations.NotNull;

/**
 * The implementation of the {@link BaseRoom} is used to test the room system.
 * It's a simple room with a shape of a cube.
 *
 * @author theEvilReaper
 * @version 1.0.0
 * @since 1.0.0
 */
public final class TestRoom extends BaseRoom {

    public static final Shape<Vec> VEC_SHAPE = new CuboidShape<>(Vec.ZERO, new Vec(10, 10, 10), DefaultPositionCalculator::calculatePositions);

    public TestRoom(@NotNull Key identifier) {
        super(identifier, VEC_SHAPE);
    }
}
