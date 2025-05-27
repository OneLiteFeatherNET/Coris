package net.onelitefeather.coris.floor;

import net.kyori.adventure.key.Key;
import net.onelitefeather.coris.room.Room;
import net.onelitefeather.coris.util.TestComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CorisFloorComponentTest {

    @Test
    void testComponentWorkFlow() {
        // Create a CorisFloor instance
        CorisFloor<Room> floor = new CorisFloor<>(Key.key("test_floor"));

        // Add a component
        TestComponent component = new TestComponent("Test");
        floor.add(TestComponent.class, component);

        // Check if the component was added
        assertTrue(floor.has(TestComponent.class));

        TestComponent fetchedComponent = floor.get(TestComponent.class);
        assertNotNull(fetchedComponent);
        assertEquals(component, fetchedComponent);

        // Remove the component
        TestComponent removedComponent = floor.remove(TestComponent.class);
        assertNotNull(removedComponent);
        assertEquals(component, removedComponent);

        // Verify the component is no longer present
        assertFalse(floor.has(TestComponent.class));
    }
}