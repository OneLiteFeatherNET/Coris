package net.onelitefeather.coris.room;

import net.kyori.adventure.key.Key;
import net.onelitefeather.coris.objects.TestRoom;
import net.onelitefeather.coris.shape.CuboidShape;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void testRoomCreation() {
        Room room = new TestRoom(Key.key("test_room"));
        assertNotNull(room);
        assertEquals(Key.key("test_room"), room.identifier());
        assertInstanceOf(CuboidShape.class, room.shape());
    }

    @Test
    void testRoomComparison() {
        Room roomA = new TestRoom(Key.key("coris", "room"));
        Room roomB = new TestRoom(Key.key("coris", "second_room"));

        assertTrue(roomA.compareTo(roomB) < 0, "Room A should be less than Room B based on alphabetical key sorting");
        assertTrue(roomB.compareTo(roomA) > 0, "Room B should be greater than Room A");
        assertEquals(0, roomA.compareTo(roomA), "A room compared with itself should return 0");
    }

    @Test
    void testRoomEqualityIsBasedOnIdentifier() {
        Key key = Key.key("coris", "room");
        Room roomA1 = new TestRoom(key);
        Room roomA2 = new TestRoom(key);
        Room roomB = new TestRoom(Key.key("coris", "another_room"));

        assertEquals(roomA1, roomA2, "Rooms with the same identifier should be equal");
        assertEquals(roomA1.hashCode(), roomA2.hashCode(), "Equal rooms must have equal hash codes");
        assertNotEquals(roomA1, roomB, "Rooms with different identifiers should not be equal");
    }

    @Test
    void testRoomEqualsConsistentWithCompareTo() {
        Key key = Key.key("coris", "room");
        Room roomA1 = new TestRoom(key);
        Room roomA2 = new TestRoom(key);

        assertEquals(0, roomA1.compareTo(roomA2), "compareTo must return 0 for rooms with the same identifier");
        assertEquals(roomA1, roomA2, "equals must agree with compareTo returning 0, per the Comparable contract");
    }

    @Test
    void testRoomHashSetDeduplicatesByIdentifier() {
        Key key = Key.key("coris", "room_set");
        Room roomA1 = new TestRoom(key);
        Room roomA2 = new TestRoom(key);

        Set<Room> rooms = new HashSet<>();
        rooms.add(roomA1);
        rooms.add(roomA2);

        assertEquals(1, rooms.size(), "HashSet should deduplicate rooms sharing the same identifier");
    }
}