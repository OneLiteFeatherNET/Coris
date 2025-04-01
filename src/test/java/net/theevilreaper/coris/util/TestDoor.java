package net.theevilreaper.coris.util;

import net.kyori.adventure.key.Key;
import net.minestom.server.coordinate.Point;
import net.theevilreaper.coris.door.BaseDoor;
import net.theevilreaper.coris.door.DoorFace;
import net.theevilreaper.coris.shape.Shape;
import org.jetbrains.annotations.NotNull;

public final class TestDoor extends BaseDoor {

    public TestDoor(@NotNull Key key, @NotNull DoorFace face, @NotNull Shape<? extends Point> shape) {
        super(key, face, shape);
    }

    @Override
    public void open() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void unlock() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
