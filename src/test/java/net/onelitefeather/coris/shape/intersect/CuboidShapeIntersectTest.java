package net.onelitefeather.coris.shape.intersect;

import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Vec;
import net.onelitefeather.coris.shape.CuboidShape;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CuboidShapeIntersectTest {

    private static final CuboidShape SHAPE =
            new CuboidShape(new Vec(0, 0, 0), new Vec(5, 5, 5));

    static Stream<Arguments> intersectProvider() {
        return Stream.of(
                Arguments.of(new Vec(2, 2, 2), true, "Inside shape"),
                Arguments.of(new Vec(0, 0, 0), true, "On start border"),
                Arguments.of(new Vec(5, 5, 5), true, "On end border"),
                Arguments.of(new Vec(6, 6, 6), false, "Outside shape"),
                Arguments.of(new Vec(-1, 2, 2), false, "Negative X outside"),
                Arguments.of(new Vec(2, 6, 2), false, "Outside only Y")
        );
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("intersectProvider")
    void testIntersect(Point position, boolean expected, String description) {
        assertEquals(expected, SHAPE.intersect(position));
    }
}
