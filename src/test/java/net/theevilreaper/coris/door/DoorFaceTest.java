package net.theevilreaper.coris.door;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DoorFaceTest {

    @ParameterizedTest(name = "Test invalid mapping for id {0}")
    @ValueSource(ints = {-1, 5})
    void testInvalidIdMapping(int id) {
        assertTrue(DoorFace.getFace(id).isEmpty());
    }

    @ParameterizedTest(name = "Test face mapping with id {0}")
    @ValueSource(ints = {0, 1, 2, 3})
    void testValidMappingById(int id) {
        Optional<DoorFace> doorFace = DoorFace.getFace(id);
        assertTrue(doorFace.isPresent());
        DoorFace face = doorFace.get();
        assertEquals(id, face.ordinal());
        assertNotNull(face.direction());
    }

    @ParameterizedTest(name = "Test face mapping with name {0}")
    @ValueSource(strings = {"north", "east", "south", "west"})
    void testMappingByName(String name) {
        Optional<DoorFace> doorFace = DoorFace.getFace(name);
        assertTrue(doorFace.isPresent());
        assertEquals(name, doorFace.get().getName());
    }

    @ParameterizedTest(name = "Test face mapping with additional name {0}")
    @ValueSource(strings = {"up", "right", "down", "left"})
    void testMappingByAdditionalName(String name) {
        Optional<DoorFace> doorFace = DoorFace.getFace(name);
        assertTrue(doorFace.isPresent());
        assertEquals(name, doorFace.get().getAlias());
    }
}