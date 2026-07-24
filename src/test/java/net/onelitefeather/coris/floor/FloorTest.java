package net.onelitefeather.coris.floor;

import net.kyori.adventure.key.Key;
import net.onelitefeather.coris.objects.TestRoom;
import net.onelitefeather.coris.room.Room;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
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
    void testFloorAddOverwritesExistingRoomAtSameKey() {
        Key roomKey = Key.key("overwrite_room_key");
        Room original = new TestRoom(roomKey);
        Room replacement = new TestRoom(roomKey);

        floor.add(roomKey, original);
        floor.add(roomKey, replacement);

        Room stored = floor.getData().get(roomKey);
        assertSame(replacement, stored, "add() should replace the room already stored at the same key");
    }

    @Test
    void testIdentifier() {
        UUID randomId = UUID.randomUUID();
        assertNotEquals(floor.identifier(), Key.key(randomId.toString()));
    }

    @Test
    void testConstructorDoesNotAliasCallerSuppliedDataMap() {
        Key roomKey = Key.key("aliasing_test_room");
        Map<Key, Room> callerData = new HashMap<>();
        callerData.put(roomKey, new TestRoom(roomKey));

        CorisFloor<Room> isolatedFloor = new CorisFloor<>(Key.key("aliasing_test_floor"), callerData, Map.of(), null);

        callerData.put(Key.key("intruder_room"), new TestRoom(Key.key("intruder_room")));

        assertEquals(1, isolatedFloor.getData().size(),
                "Mutating the caller's map after construction must not affect the floor's internal data");
        assertFalse(isolatedFloor.getData().containsKey(Key.key("intruder_room")));
    }

    @Test
    void testGetDataReturnsLiveUnmodifiableView() {
        Key roomKey = Key.key("live_view_room");
        Map<Key, Room> view = floor.getData();
        assertFalse(view.containsKey(roomKey));

        floor.add(roomKey, new TestRoom(roomKey));

        assertTrue(view.containsKey(roomKey),
                "getData() should return a live view backed by the floor's internal data, not a snapshot copy");
        assertThrows(UnsupportedOperationException.class,
                () -> view.put(Key.key("rejected_room"), new TestRoom(Key.key("rejected_room"))));
    }
}
