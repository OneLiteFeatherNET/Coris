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
    void testMinMaxGetter() {
        PointShape pointShape = new PointShape(new Vec(5, 5, 5));
        assertEquals(new Vec(5, 5, 5), pointShape.min());
        assertEquals(new Vec(5, 5, 5), pointShape.max());
        assertEquals(pointShape.max(), pointShape.min(), "Min and max should be equal for a point shape");
    }
}
