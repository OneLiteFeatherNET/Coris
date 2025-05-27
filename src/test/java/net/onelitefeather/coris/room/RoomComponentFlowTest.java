package net.onelitefeather.coris.room;

import net.kyori.adventure.key.Key;
import net.onelitefeather.coris.component.CorisComponent;
import net.onelitefeather.coris.objects.TestRoom;
import net.onelitefeather.coris.util.TestComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomComponentFlowTest {


    @Test
    void testRoomMetaDataFlow() {
        TestComponent importantComponent = new TestComponent("important data");
        assertNotNull(importantComponent);
        assertInstanceOf(TestComponent.class, importantComponent);

        Room room = new TestRoom(Key.key("room:test"));

        assertNotNull(room);
        assertInstanceOf(TestRoom.class, room);
        assertFalse(room.has(TestComponent.class));

        room.add(TestComponent.class, importantComponent);

        assertTrue(room.has(TestComponent.class));

        CorisComponent component = room.get(TestComponent.class);

        assertNotNull(component);
        assertEquals(importantComponent, component);
    }
}
