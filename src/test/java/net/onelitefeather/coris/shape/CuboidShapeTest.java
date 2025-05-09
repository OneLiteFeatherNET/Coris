package net.onelitefeather.coris.shape;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.onelitefeather.coris.util.DefaultPositionCalculator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CuboidShapeTest {

    @Test
    void testInvalidCuboidCreation() {
        assertThrowsExactly(
                IllegalArgumentException.class,
                () -> new CuboidShape<>(Pos.ZERO, Pos.ZERO, DefaultPositionCalculator::calculatePositions),
                "The cuboid shape should not be created with the same position"
        );
    }

    @Test
    void testSuccessfullyTwoDIntersect() {
        Shape<Vec> cuboidShape = new CuboidShape<>(Vec.ZERO, new Vec(10, 10, 10), DefaultPositionCalculator::calculatePositions);
        assertInstanceOf(Shape.class, cuboidShape);
        assertInstanceOf(CuboidShape.class, cuboidShape);

        List<Vec> validPositions = new ArrayList<>();

        validPositions.add(new Vec(5, 5, 5));
        validPositions.add(new Vec(10, 10, 10));
        validPositions.add(new Vec(0, 0, 0));

        for (Vec validPosition : validPositions) {
            assertTrue(cuboidShape.intersect2D(validPosition));
        }
    }

    @Test
    void testInvalidTwoDIntersect() {
        Shape<Vec> cuboidShape = new CuboidShape<>(Vec.ZERO, new Vec(10, 10, 10), DefaultPositionCalculator::calculatePositions);
        assertInstanceOf(Shape.class, cuboidShape);
        assertInstanceOf(CuboidShape.class, cuboidShape);

        List<Vec> invalidPositions = new ArrayList<>();

        invalidPositions.add(new Vec(11, 11, 11));
        invalidPositions.add(new Vec(-1, -1, -1));

        for (Vec invalidPosition : invalidPositions) {
            assertFalse(cuboidShape.intersect2D(invalidPosition));
        }
    }
}
