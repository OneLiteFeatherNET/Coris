package net.onelitefeather.coris.floor;

import net.kyori.adventure.key.Key;
import net.onelitefeather.coris.objects.TestRoom;
import net.onelitefeather.coris.room.Room;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FloorTest {

    private static final Key TEST_KEY = Key.key("test");
    private static CorisFloor<Room> floor;

    @BeforeAll
    static void setUp() {
        String uuidString = UUID.randomUUID().toString();
        floor = new CorisFloor<>(Key.key(uuidString));
    }

    @AfterEach
    void tearDown() {
        floor.clear();
    }

    @AfterAll
    static void tearDownAll() {
        floor = null;
    }

    @Test
    void testFloorCreation() {
        assertTrue(floor.isEmpty());
        floor.add(TEST_KEY, new TestRoom(Key.key("test_room")));
        assertFalse(floor.isEmpty());
    }

    @Test
    void testFloorGet() {
        floor.add(TEST_KEY, new TestRoom(Key.key("test_room")));
        assertFalse(floor.isEmpty());
        Room test = floor.getData().get(TEST_KEY);
        assertNotNull(test);
        assertInstanceOf(TestRoom.class, test);
        assertInstanceOf(Room.class, test);
    }

    @Test
    void testIdentifier() {
        UUID randomId = UUID.randomUUID();
        assertNotEquals(floor.identifier(), Key.key(randomId.toString()));
    }
}
