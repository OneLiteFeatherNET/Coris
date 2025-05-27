package net.onelitefeather.coris.util;

import net.kyori.adventure.key.Key;
import net.minestom.server.coordinate.Point;
import net.onelitefeather.coris.door.AnimationState;
import net.onelitefeather.coris.door.BaseDoor;
import net.onelitefeather.coris.door.DoorFace;
import net.onelitefeather.coris.shape.Shape;
import org.jetbrains.annotations.NotNull;

public final class TestDoor extends BaseDoor {

    public TestDoor(@NotNull Key key, @NotNull DoorFace face, @NotNull Shape<? extends Point> shape) {
        super(key, face, shape);
    }

    @Override
    public void playAnimation(@NotNull AnimationState animationState) {
        throw new UnsupportedOperationException("Not supported yet.");
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
