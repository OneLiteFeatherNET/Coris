package net.onelitefeather.coris.shape;

import net.minestom.server.coordinate.Vec;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointShapeTest {

    @Test
    void testPointShapeCreation() {
        PointShape pointShape = new PointShape(new Vec(5, 5, 5));
        assertInstanceOf(Shape.class, pointShape);
        assertInstanceOf(PointShape.class, pointShape);
        assertEquals(new Vec(5, 5, 5), pointShape.position());
    }

    @Test
    void testPointShapeComparison() {
        PointShape pointShape1 = new PointShape(new Vec(5, 5, 5));
        PointShape pointShape2 = new PointShape(new Vec(5, 5, 5));
        PointShape pointShape3 = new PointShape(new Vec(10, 10, 10));

        assertEquals(0, pointShape1.compareTo(pointShape2), "Point shapes with the same position should be equal");
        assertTrue(pointShape1.compareTo(pointShape3) < 0, "Point shape with lower coordinates should be less than one with higher coordinates");
    }

    @Test
    void testPointShape2DIntersection() {
        PointShape pointShape = new PointShape(new Vec(5, 5, 5));

        assertTrue(pointShape.intersect2D(new Vec(5, 5, 0)), "Point shape should intersect with the same x and y coordinates");
        assertFalse(pointShape.intersect2D(new Vec(6, 5, 0)), "Point shape should not intersect with different x coordinate");
        assertFalse(pointShape.intersect2D(new Vec(5, 6, 0)), "Point shape should not intersect with different y coordinate");
    }

    @Test
    void testPointShape3DIntersection() {
        PointShape pointShape = new PointShape(new Vec(5, 5, 5));

        assertTrue(pointShape.intersect3D(new Vec(5, 5, 5)), "Point shape should intersect with the same coordinates in 3D");
        assertFalse(pointShape.intersect3D(new Vec(6, 5, 5)), "Point shape should not intersect with different x coordinate in 3D");
        assertFalse(pointShape.intersect3D(new Vec(5, 6, 5)), "Point shape should not intersect with different y coordinate in 3D");
        assertFalse(pointShape.intersect3D(new Vec(5, 5, 6)), "Point shape should not intersect with different z coordinate in 3D");
    }
}