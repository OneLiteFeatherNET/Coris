package net.onelitefeather.coris.component;

import net.onelitefeather.coris.util.TestComponent;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ComponentContainerTest {

    @Test
    void testAddAndGet() {
        ComponentContainer container = new ComponentContainer();
        assertFalse(container.has(TestComponent.class));

        TestComponent component = new TestComponent("data");
        container.add(TestComponent.class, component);

        assertTrue(container.has(TestComponent.class));
        assertEquals(component, container.get(TestComponent.class));
    }

    @Test
    void testAddOverwritesExisting() {
        ComponentContainer container = new ComponentContainer();
        TestComponent original = new TestComponent("original");
        TestComponent replacement = new TestComponent("replacement");

        container.add(TestComponent.class, original);
        container.add(TestComponent.class, replacement);

        TestComponent retrieved = container.get(TestComponent.class);
        assertNotNull(retrieved);
        assertEquals("replacement", retrieved.data());
    }

    @Test
    void testRemove() {
        ComponentContainer container = new ComponentContainer();
        TestComponent component = new TestComponent("data");
        container.add(TestComponent.class, component);

        TestComponent removed = container.remove(TestComponent.class);

        assertEquals(component, removed);
        assertFalse(container.has(TestComponent.class));
        assertNull(container.get(TestComponent.class));
    }

    @Test
    void testConstructorDefensivelyCopiesSeedMap() {
        Map<Class<? extends CorisComponent>, CorisComponent> seed = new HashMap<>();
        seed.put(TestComponent.class, new TestComponent("seed"));

        ComponentContainer container = new ComponentContainer(seed);
        seed.put(TestComponent.class, new TestComponent("mutated-after-construction"));

        TestComponent stored = container.get(TestComponent.class);
        assertNotNull(stored);
        assertEquals("seed", stored.data());
    }

    @Test
    void testClear() {
        ComponentContainer container = new ComponentContainer();
        container.add(TestComponent.class, new TestComponent("data"));
        assertTrue(container.has(TestComponent.class));

        container.clear();

        assertFalse(container.has(TestComponent.class));
    }
}
