package net.onelitefeather.coris.door;

import net.kyori.adventure.key.Key;
import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Vec;
import net.onelitefeather.coris.PositionBase;
import net.onelitefeather.coris.shape.CuboidShape;
import net.onelitefeather.coris.shape.Shape;
import net.onelitefeather.coris.util.TestDoor;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BaseDoorTest {

    @Test
    void testDoorCreation() {
        Key key = Key.key("door:test");
        Door door = new TestDoor(key, DoorFace.NORTH, new CuboidShape<Point>(Vec.ZERO, new Vec(1, 1, 1)));
        assertNotNull(door);
        assertEquals(DoorFace.NORTH, door.face());
        assertNotEquals(UUID.randomUUID(), door.id());
        assertEquals(key, door.key());
        assertFalse(door.isLocked());

        Shape<? extends Point> shape = door.shape();
        assertNotNull(shape);
        assertInstanceOf(CuboidShape.class, shape);

        door.setLocked(true);

        assertTrue(door.isLocked());
    }
}
