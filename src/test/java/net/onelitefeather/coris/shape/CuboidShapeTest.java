package net.onelitefeather.coris.shape;

import net.minestom.server.coordinate.Vec;
import org.junit.jupiter.api.Test;

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
    void testCoordinateNormalization() {
        // Create a cuboid with reversed coordinates (start is max, end is min)
        Vec highPoint = new Vec(5, 6, 7);
        Vec lowPoint = new Vec(0, 1, 2);

        CuboidShape shape = new CuboidShape(highPoint, lowPoint);

        // Verify that start is normalized to the minimum coordinates
        assertEquals(0, shape.start().blockX());
        assertEquals(1, shape.start().blockY());
        assertEquals(2, shape.start().blockZ());

        // Verify that end is normalized to the maximum coordinates
        assertEquals(5, shape.end().blockX());
        assertEquals(6, shape.end().blockY());
        assertEquals(7, shape.end().blockZ());
    }

    @Test
    void testIntersect() {
        CuboidShape shape = new CuboidShape(new Vec(0, 0, 0), new Vec(4, 4, 4));

        // inside
        assertTrue(shape.intersect(new Vec(2, 2, 2)));

        // boundary
        assertTrue(shape.intersect(new Vec(0, 2, 4)));

        // outside X
        assertFalse(shape.intersect(new Vec(-1, 2, 2)));
        assertFalse(shape.intersect(new Vec(5, 2, 2)));

        // outside Y
        assertFalse(shape.intersect(new Vec(2, -1, 2)));
        assertFalse(shape.intersect(new Vec(2, 5, 2)));

        // outside Z
        assertFalse(shape.intersect(new Vec(2, 2, -1)));
        assertFalse(shape.intersect(new Vec(2, 2, 5)));
    }

    @Test
    void testCompareTo() {
        Shape shape1 = new CuboidShape(new Vec(0, 0, 0), new Vec(5, 5, 5));
        Shape shape2 = new CuboidShape(new Vec(0, 0, 0), new Vec(5, 5, 5));
        Shape shape3 = new CuboidShape(new Vec(1, 1, 1), new Vec(6, 6, 6));

        assertEquals(-1, shape1.compareTo(new PointShape(Vec.ZERO)));
        assertEquals(0, shape1.compareTo(shape2), "Shapes with the same coordinates should be equal");
        assertTrue(shape1.compareTo(shape3) < 0, "Shape with lower coordinates should be less than one with higher coordinates");
    }

    @Test
    void testMinMaxAreNormalizedCorrectly() {
        Vec a = new Vec(5, 1, 7);
        Vec b = new Vec(0, 6, 2);

        CuboidShape shape = new CuboidShape(a, b);

        assertEquals(0, shape.min().blockX());
        assertEquals(1, shape.min().blockY());
        assertEquals(2, shape.min().blockZ());

        assertEquals(5, shape.max().blockX());
        assertEquals(6, shape.max().blockY());
        assertEquals(7, shape.max().blockZ());
    }

    @Test
    void testMinMaxAreStable() {
        Vec a = new Vec(3, 3, 3);
        Vec b = new Vec(10, 10, 10);

        CuboidShape shape = new CuboidShape(a, b);

        assertEquals(shape.min(), shape.min());
        assertEquals(shape.max(), shape.max());
    }

    @Test
    void testMinMaxSymmetry() {
        Vec a = new Vec(8, 2, 5);
        Vec b = new Vec(1, 9, 3);

        CuboidShape shape = new CuboidShape(a, b);

        // min must never exceed max
        assertTrue(shape.min().blockX() <= shape.max().blockX());
        assertTrue(shape.min().blockY() <= shape.max().blockY());
        assertTrue(shape.min().blockZ() <= shape.max().blockZ());
    }
}
