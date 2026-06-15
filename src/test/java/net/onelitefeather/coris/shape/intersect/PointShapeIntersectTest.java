package net.onelitefeather.coris.shape.intersect;

import net.minestom.server.coordinate.Vec;
import net.onelitefeather.coris.shape.PointShape;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointShapeIntersectTest {

    private static final PointShape SHAPE = new PointShape(new Vec(2, 2, 2));

    static Stream<Arguments> intersect3DProvider() {
        return Stream.of(
                Arguments.of(new Vec(2, 2, 2), true, "Exact match"),
                Arguments.of(new Vec(3, 2, 2), false, "X mismatch"),
                Arguments.of(new Vec(2, 3, 2), false, "Y mismatch"),
                Arguments.of(new Vec(2, 2, 3), false, "Z mismatch")
        );
    }

    static Stream<Arguments> intersect2DProvider() {
        return Stream.of(
                Arguments.of(new Vec(2, 2, 2), true, "Exact match"),
                Arguments.of(new Vec(3, 2, 2), false, "X mismatch"),
                Arguments.of(new Vec(2, 3, 2), true, "Y ignored"),
                Arguments.of(new Vec(2, 2, 3), false, "Z mismatch")
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