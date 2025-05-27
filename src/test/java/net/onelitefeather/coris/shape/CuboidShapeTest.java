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
                () -> new CuboidShape(Vec.ZERO, Vec.ZERO),
                "The cuboid shape should not be created with the same position"
        );
    }

    @Test
    void testSuccessfullyTwoDIntersect() {
        Shape<Vec> cuboidShape = new CuboidShape(Vec.ZERO, new Vec(10, 10, 10));
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
        Shape<Vec> cuboidShape = new CuboidShape(Vec.ZERO, new Vec(10, 10, 10));
        assertInstanceOf(Shape.class, cuboidShape);
        assertInstanceOf(CuboidShape.class, cuboidShape);

        List<Vec> invalidPositions = new ArrayList<>();

        invalidPositions.add(new Vec(11, 11, 11));
        invalidPositions.add(new Vec(-1, -1, -1));

        for (Vec invalidPosition : invalidPositions) {
            assertFalse(cuboidShape.intersect2D(invalidPosition));
        }
    }

    @Test
    void testSuccessfully3DIntersect() {
        Shape<Vec> cuboidShape = new CuboidShape(Vec.ZERO, new Vec(10, 10, 10));
        assertInstanceOf(Shape.class, cuboidShape);
        assertInstanceOf(CuboidShape.class, cuboidShape);

        List<Vec> validPositions = new ArrayList<>();

        validPositions.add(new Vec(5, 5, 5));
        validPositions.add(new Vec(10, 10, 10));
        validPositions.add(new Vec(0, 0, 0));

        for (Vec validPosition : validPositions) {
            assertTrue(cuboidShape.intersect3D(validPosition));
        }
    }

    @Test
    void testSuccessfully2DIntersect() {
        Shape<Vec> cuboidShape = new CuboidShape(Vec.ZERO, new Vec(10, 10, 10));
        assertInstanceOf(Shape.class, cuboidShape);
        assertInstanceOf(CuboidShape.class, cuboidShape);

        List<Vec> validPositions = new ArrayList<>();

        validPositions.add(new Vec(5, 5, 0));
        validPositions.add(new Vec(10, 10, 0));
        validPositions.add(new Vec(0, 0, 0));

        for (Vec validPosition : validPositions) {
            assertTrue(cuboidShape.intersect2D(validPosition), "Expected intersection for position: " + validPosition);
        }

        List<Vec> invalidPositions = new ArrayList<>();

        invalidPositions.add(new Vec(11, 11, 0));
        invalidPositions.add(new Vec(-1, -1, 0));

        for (Vec invalidPosition : invalidPositions) {
            assertFalse(cuboidShape.intersect2D(invalidPosition), "Expected no intersection for position: " + invalidPosition);
        }
    }

    @Test
    void testCompareTo() {
        Shape<Vec> shape1 = new CuboidShape(new Vec(0, 0, 0), new Vec(5, 5, 5));
        Shape<Vec> shape2 = new CuboidShape(new Vec(0, 0, 0), new Vec(5, 5, 5));
        Shape<Vec> shape3 = new CuboidShape(new Vec(1, 1, 1), new Vec(6, 6, 6));

        assertEquals(-1, shape1.compareTo(new PointShape(Vec.ZERO)));
        assertEquals(0, shape1.compareTo(shape2), "Shapes with the same coordinates should be equal");
        assertTrue(shape1.compareTo(shape3) < 0, "Shape with lower coordinates should be less than one with higher coordinates");
    }
}
