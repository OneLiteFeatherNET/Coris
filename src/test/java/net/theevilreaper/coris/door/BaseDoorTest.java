package net.theevilreaper.coris.door;

import net.kyori.adventure.key.Key;
import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Vec;
import net.theevilreaper.coris.shape.CuboidShape;
import net.theevilreaper.coris.shape.Shape;
import net.theevilreaper.coris.util.DefaultPositionCalculator;
import net.theevilreaper.coris.util.TestDoor;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BaseDoorTest {

    @Test
    void testDoorCreation() {
        Key key = Key.key("door:test");
        Door door = new TestDoor(key, DoorFace.NORTH, new CuboidShape<Point>(Vec.ZERO, new Vec(1, 1, 1), DefaultPositionCalculator::calculatePositions));
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
