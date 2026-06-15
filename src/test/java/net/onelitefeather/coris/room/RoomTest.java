package net.onelitefeather.coris.room;

import net.kyori.adventure.key.Key;
import net.onelitefeather.coris.objects.TestRoom;
import net.onelitefeather.coris.shape.CuboidShape;
import org.junit.jupiter.api.Test;

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
        Room roomA = new TestRoom(Key.key("coris:room_a"));
        Room roomB = new TestRoom(Key.key("coris:room_b"));

        assertTrue(roomA.compareTo(roomB) < 0, "Room A should be less than Room B based on alphabetical key sorting");
        assertTrue(roomB.compareTo(roomA) > 0, "Room B should be greater than Room A");
        assertEquals(0, roomA.compareTo(roomA), "A room compared with itself should return 0");
    }
}