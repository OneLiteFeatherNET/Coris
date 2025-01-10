package net.theevilreaper.coris.floor;

import net.kyori.adventure.key.Key;
import net.theevilreaper.coris.room.Room;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorRegistryTest {

    private static FloorRegistry<Floor<Room>> floorRegistry;

    @BeforeAll
    static void setup() {
        floorRegistry = new CorisFloorRegistry<>();
        assertTrue(floorRegistry.getFloors().isEmpty());
    }

    @AfterEach
    void tearDown() {
        floorRegistry.clear();
        assertTrue(floorRegistry.getFloors().isEmpty());
    }

    @AfterAll
    static void tearDownAll() {
        floorRegistry = null;
        assertNull(floorRegistry);
    }

    @Test
    void testRegistryAdd() {
        Floor<Room> testFloor = new CorisFloor<>(Key.key("test_floor"));
        floorRegistry.add(testFloor.identifier(), testFloor);
        assertFalse(floorRegistry.getFloors().isEmpty());
    }
}
