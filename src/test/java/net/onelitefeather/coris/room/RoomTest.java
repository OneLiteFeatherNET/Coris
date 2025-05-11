package net.onelitefeather.coris.room;

import net.kyori.adventure.key.Key;
import net.onelitefeather.coris.objects.TestRoom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void testRoomCreation() {
        Room room = new TestRoom(Key.key("test_room"));
        assertNotNull(room);
        assertEquals(Key.key("test_room"), room.identifier());
    }
}