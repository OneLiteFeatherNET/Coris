package net.onelitefeather.coris.shape;

import net.minestom.server.coordinate.Vec;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CuboidShapeIntersectTest {

    private static final CuboidShape SHAPE = new CuboidShape(new Vec(0, 0, 0), new Vec(5, 5, 5));

    static Stream<Arguments> intersect3DProvider() {
        return Stream.of(
                Arguments.of(new Vec(2, 2, 2), true, "Inside shape"),
                Arguments.of(new Vec(0, 0, 0), true, "On start border"),
                Arguments.of(new Vec(5, 5, 5), true, "On end border"),
                Arguments.of(new Vec(6, 6, 6), false, "Outside shape"),
                Arguments.of(new Vec(-1, 2, 2), false, "Negative X outside"),
                Arguments.of(new Vec(2, 6, 2), false, "Outside only Y")
        );
    }

    static Stream<Arguments> intersect2DProvider() {
        return Stream.of(
                Arguments.of(new Vec(2, 99, 2), true, "Inside, Y ignored"),
                Arguments.of(new Vec(0, 0, 0), true, "On start border"),
                Arguments.of(new Vec(5, 0, 5), true, "On end border XZ"),
                Arguments.of(new Vec(6, 2, 2), false, "Outside X"),
                Arguments.of(new Vec(2, 2, 6), false, "Outside Z"),
                Arguments.of(new Vec(2, 999, 2), true, "Large Y ignored"),
                Arguments.of(new Vec(2, -999, 2), true, "Negative Y ignored")
        );
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("intersect3DProvider")
    void testIntersect3D(Vec position, boolean expected, String description) {
        assertEquals(expected, SHAPE.intersect3D(position));
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("intersect2DProvider")
    void testIntersect2D(Vec position, boolean expected, String description) {
        assertEquals(expected, SHAPE.intersect2D(position));
    }
}
