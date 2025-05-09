package net.onelitefeather.coris.door.event;

import net.kyori.adventure.key.Key;
import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.Instance;
import net.minestom.testing.Env;
import net.minestom.testing.extension.MicrotusExtension;
import net.onelitefeather.coris.door.Door;
import net.onelitefeather.coris.door.DoorFace;
import net.onelitefeather.coris.shape.CuboidShape;
import net.onelitefeather.coris.util.DefaultPositionCalculator;
import net.onelitefeather.coris.util.TestDoor;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MicrotusExtension.class)
class DoorOpenEventIntegrationTest {

    @Test
    void testEventConstruction(@NotNull Env env) {
        Instance instance = env.createFlatInstance();
        Key key = Key.key("door:test");
        Door door = new TestDoor(key, DoorFace.EAST, new CuboidShape<Point>(Pos.ZERO, new Pos(1, 1, 1), DefaultPositionCalculator::calculatePositions));
        assertNotNull(door);

        DoorOpenEvent event = new DoorOpenEvent(door, instance);
        assertNotNull(event);
        assertEquals(instance, event.getInstance());
        assertFalse(event.isCancelled());
        assertEquals(door, event.getDoor());

        event.setCancelled(true);

        assertTrue(event.isCancelled());

        env.destroyInstance(instance);
    }
}
