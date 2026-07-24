package net.onelitefeather.coris.floor;

import net.kyori.adventure.key.Key;
import net.minestom.server.coordinate.Vec;
import net.onelitefeather.coris.room.BaseRoom;
import net.onelitefeather.coris.room.Room;
import net.onelitefeather.coris.shape.CuboidShape;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FloorRegistryTest {

    private static FloorRegistry<Floor<Room>> floorRegistry;

    @BeforeAll
    static void setup() {
        floorRegistry = new CorisFloorRegistry<>();
        assertTrue(floorRegistry.getFloors().isEmpty());
    }

    @AfterEach
    void tearDown() {
        floorRegistry.clear();
        assertTrue(floorRegistry.getFloors().isEmpty());
    }

    @AfterAll
    static void tearDownAll() {
        floorRegistry = null;
        assertNull(floorRegistry);
    }

    @Test
    void testRegistryAdd() {
        Floor<Room> testFloor = new CorisFloor<>(Key.key("test_floor"), new CuboidShape(Vec.ZERO, new Vec(5, 5, 5)));
        floorRegistry.add(testFloor.identifier(), testFloor);
        assertFalse(floorRegistry.getFloors().isEmpty());
    }

    @Test
    void testRegistryRemove() {
        Key floorKey = Key.key("coris", "lobby");
        Floor<Room> testFloor = new CorisFloor<>(floorKey, new CuboidShape(Vec.ZERO, new Vec(5, 5, 5)));

        floorRegistry.add(floorKey, testFloor);
        assertTrue(floorRegistry.get(floorKey).isPresent());

        floorRegistry.remove(floorKey);
        assertFalse(floorRegistry.get(floorKey).isPresent());
        assertTrue(floorRegistry.getFloors().isEmpty());
    }

    @Test
    void testRegistrySortingPreservation() {
        // Create floors out of level order
        Floor<Room> floor3 = new CorisFloor<>(Key.key("coris", "f3"), Map.of(), Map.of(), new CuboidShape(new Vec(0, 10, 0), new Vec(5, 15, 5)));
        Floor<Room> floor1 = new CorisFloor<>(Key.key("coris", "f1"), Map.of(), Map.of(), new CuboidShape(new Vec(0, 0, 0), new Vec(5, 5, 5)));
        Floor<Room> floor2 = new CorisFloor<>(Key.key("coris", "f2"), Map.of(), Map.of(), new CuboidShape(new Vec(0, 5, 0), new Vec(5, 10, 5)));

        floorRegistry.add(floor3.identifier(), floor3);
        floorRegistry.add(floor1.identifier(), floor1);
        floorRegistry.add(floor2.identifier(), floor2);

        Comparator<Floor<Room>> byHeight = Comparator.comparingDouble(f -> f.shape() instanceof CuboidShape c ? c.start().y() : 0);

        Map<Key, Floor<Room>> sortedFloors = floorRegistry.getFloors(byHeight);

        var iterator = sortedFloors.values().iterator();
        assertEquals(floor1, iterator.next());
        assertEquals(floor2, iterator.next());
        assertEquals(floor3, iterator.next());
    }

    @Test
    void testSpatialFloorAndRoomResolution() {
        Key floorKey = Key.key("coris", "ground_floor");
        Floor<Room> floor = new CorisFloor<>(floorKey, new CuboidShape(new Vec(0, 0, 0), new Vec(10, 5, 10)));

        Room kitchen = new BaseRoom(Key.key("coris", "kitchen"), new CuboidShape(new Vec(0, 0, 0), new Vec(5, 5, 5)));
        floor.add(kitchen.identifier(), kitchen);

        floorRegistry.add(floorKey, floor);

        Vec pointInKitchen = new Vec(2, 2, 2);

        Optional<Floor<Room>> foundFloor = floorRegistry.getFloorAt(pointInKitchen);
        assertTrue(foundFloor.isPresent());
        assertEquals(floor, foundFloor.get());

        Optional<? extends Room> foundRoom = floorRegistry.getRoomAt(pointInKitchen);
        assertTrue(foundRoom.isPresent());
        assertEquals(kitchen, foundRoom.get());

        Vec pointOutsideKitchen = new Vec(8, 2, 8);

        assertTrue(floorRegistry.getFloorAt(pointOutsideKitchen).isPresent());
        assertFalse(floorRegistry.getRoomAt(pointOutsideKitchen).isPresent());

        Vec pointOutOfBounds = new Vec(2, 10, 2);
        assertFalse(floorRegistry.getFloorAt(pointOutOfBounds).isPresent());
        assertFalse(floorRegistry.getRoomAt(pointOutOfBounds).isPresent());
    }

    @Test
    void testGetFloorAtSkipsFloorWithoutShape() {
        Key noShapeKey = Key.key("coris", "no_shape_only");
        Floor<Room> floorWithoutShape = new CorisFloor<>(noShapeKey);
        floorRegistry.add(noShapeKey, floorWithoutShape);

        Vec anyPoint = new Vec(1, 1, 1);

        assertDoesNotThrow(() -> floorRegistry.getFloorAt(anyPoint));
        assertFalse(floorRegistry.getFloorAt(anyPoint).isPresent());
    }

    @Test
    void testGetFloorAtFindsShapedFloorAlongsideShapelessFloor() {
        Key noShapeKey = Key.key("coris", "no_shape_mixed");
        Floor<Room> floorWithoutShape = new CorisFloor<>(noShapeKey);
        floorRegistry.add(noShapeKey, floorWithoutShape);

        Key shapedKey = Key.key("coris", "shaped_mixed");
        Floor<Room> shapedFloor = new CorisFloor<>(shapedKey, new CuboidShape(Vec.ZERO, new Vec(5, 5, 5)));
        floorRegistry.add(shapedKey, shapedFloor);

        Vec pointInShapedFloor = new Vec(1, 1, 1);

        Optional<Floor<Room>> found = floorRegistry.getFloorAt(pointInShapedFloor);
        assertTrue(found.isPresent());
        assertEquals(shapedFloor, found.get());
    }
}
