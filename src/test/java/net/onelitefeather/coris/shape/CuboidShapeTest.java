package net.onelitefeather.coris.shape;

import net.minestom.server.coordinate.Vec;
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
    void testCompareTo() {
        Shape shape1 = new CuboidShape(new Vec(0, 0, 0), new Vec(5, 5, 5));
        Shape shape2 = new CuboidShape(new Vec(0, 0, 0), new Vec(5, 5, 5));
        Shape shape3 = new CuboidShape(new Vec(1, 1, 1), new Vec(6, 6, 6));

        assertEquals(-1, shape1.compareTo(new PointShape(Vec.ZERO)));
        assertEquals(0, shape1.compareTo(shape2), "Shapes with the same coordinates should be equal");
        assertTrue(shape1.compareTo(shape3) < 0, "Shape with lower coordinates should be less than one with higher coordinates");
    }
}
