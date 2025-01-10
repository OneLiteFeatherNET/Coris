package net.theevilreaper.coris.room;

import net.kyori.adventure.key.Key;
import net.theevilreaper.coris.objects.TestRoom;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RoomMetadataFlowTest {

    private static final String TEST_KEY = "room_meta_key";

    @Test
    void testRoomMetaDataFlow() {
        Room room = new TestRoom(Key.key("room:test"));
        assertNotNull(room);
        assertTrue(room.metaData().isEmpty());

        room.addMetaData(TEST_KEY, 12);
        assertTrue(room.hasMetaData(TEST_KEY));

        Optional<Object> metaData = room.getMetaData(TEST_KEY);
        assertTrue(metaData.isPresent());
        assertEquals(12, metaData.get());

        room.removeMetaData(TEST_KEY);
        assertTrue(room.metaData().isEmpty());
    }
}
