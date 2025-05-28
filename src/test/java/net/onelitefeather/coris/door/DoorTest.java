package net.onelitefeather.coris.door;

import net.kyori.adventure.key.Key;
import net.minestom.server.coordinate.Vec;
import net.onelitefeather.coris.component.CorisComponent;
import net.onelitefeather.coris.shape.CuboidShape;
import net.onelitefeather.coris.shape.Shape;
import net.onelitefeather.coris.util.TestComponent;
import net.onelitefeather.coris.util.TestDoor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest {

    private static Key key;

    @BeforeAll
    static void setUp() {
        key = Key.key("door:test");
    }


    @Test
    void testDoorCreation() {
        Door door = new TestDoor(key, DoorFace.NORTH, new CuboidShape(Vec.ZERO, new Vec(1, 1, 1)));
        assertNotNull(door);
        assertEquals(DoorFace.NORTH, door.face());
        assertNotEquals(UUID.randomUUID(), door.id());
        assertEquals(key, door.key());

        Shape shape = door.shape();
        assertNotNull(shape);
        assertInstanceOf(CuboidShape.class, shape);

    }

    @Test
    void testDoorComponent() {
        Door door = new TestDoor(key, DoorFace.NORTH, new CuboidShape(Vec.ZERO, new Vec(1, 1, 1)));
        assertNotNull(door);

        assertFalse(door.has(TestComponent.class));

        TestComponent testComponent = new TestComponent("data");

        door.add(TestComponent.class, testComponent);

        assertTrue(door.has(TestComponent.class));

        TestComponent retrievedComponent = door.get(TestComponent.class);

        assertNotNull(retrievedComponent);
        assertInstanceOf(CorisComponent.class, retrievedComponent);
        assertInstanceOf(TestComponent.class, retrievedComponent);

        assertEquals(testComponent.data(), retrievedComponent.data());

        assertNotNull(door.remove(TestComponent.class));
        assertFalse(door.has(TestComponent.class));
    }
}
