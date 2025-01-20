package net.theevilreaper.coris.floor.event;

import net.kyori.adventure.key.Key;
import net.theevilreaper.coris.floor.CorisFloor;
import net.theevilreaper.coris.floor.Floor;
import net.theevilreaper.coris.objects.TestRoom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorCreateEventTest {

    @Test
    void testCreateEventConstruction() {
        Floor<TestRoom> testFloor = new CorisFloor<>(Key.key("floor:test"));
        assertNotNull(testFloor);
        FloorCreateEvent<Floor<TestRoom>> floorAddEvent = new FloorCreateEvent<>(testFloor);
        assertNotNull(floorAddEvent);
        assertEquals(testFloor, floorAddEvent.getFloor());
        assertFalse(floorAddEvent.isCancelled());

        floorAddEvent.setCancelled(true);

        assertTrue(floorAddEvent.isCancelled());
    }
}
