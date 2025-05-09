package net.onelitefeather.coris.floor;

import net.kyori.adventure.key.Key;
import net.onelitefeather.coris.room.Room;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FloorMetadataFlowTest {

    @Test
    void testMetadataFlowOfAFloor() {
        Floor<Room> testFloor = new CorisFloor<>(Key.key("floor_abc"));
        assertNotNull(testFloor);

        assertTrue(testFloor.metaData().isEmpty());

        testFloor.addMetaData("test", "test");
        assertFalse(testFloor.metaData().isEmpty());
        assertTrue(testFloor.hasMetaData("test"));
        Optional<Object> testData = testFloor.getMetaData("test");
        assertTrue(testData.isPresent());
        assertInstanceOf(String.class, testData.get());
        assertEquals("test", testData.get());

        testFloor.removeMetaData("test");
        assertTrue(testFloor.metaData().isEmpty());
    }

    @Test
    void testNegativeReturns() {
        Floor<Room> testFloor = new CorisFloor<>(Key.key("floor_abc"));
        assertNotNull(testFloor);
        assertEquals(-1L, testFloor.creationDate());
        assertEquals(-1L, testFloor.updateDate());
    }
}
