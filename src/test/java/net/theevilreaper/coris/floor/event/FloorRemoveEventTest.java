package net.theevilreaper.coris.floor.event;

import net.kyori.adventure.key.Key;
import net.theevilreaper.coris.floor.CorisFloor;
import net.theevilreaper.coris.floor.Floor;
import net.theevilreaper.coris.objects.TestRoom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorRemoveEventTest {

    @Test
    void testFloorRemoveConstructionEvent() {
        Floor<TestRoom> testFloor = new CorisFloor<>(Key.key("floor:test"));
        assertNotNull(testFloor);
        FloorRemoveEvent<Floor<TestRoom>> floorRemoveEvent = new FloorRemoveEvent<>(testFloor);
        assertNotNull(floorRemoveEvent);
        assertEquals(testFloor, floorRemoveEvent.floor());
    }
}
