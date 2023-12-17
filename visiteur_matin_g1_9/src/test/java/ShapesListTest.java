import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.shapes.ShapesList;
import fr.shapes.Square;
import fr.shapes.Triangle;


import fr.shapes.Circle;



class ShapesListTest {

    private ShapesList shapesList;

    @BeforeEach
    void setUp() {
        shapesList = new ShapesList();
    }

    @Test
    void testAddShape() {
        Circle circle = new Circle(10,25);
        shapesList.addShape(circle);
        assertEquals(1, shapesList.size());
        assertTrue(shapesList.getAllShapes().contains(circle));
    }

    @Test
    void testRemoveShape() {
        Triangle triangle = new Triangle(30,60);
        shapesList.addShape(triangle);
        shapesList.removeShape(triangle);
        assertEquals(0, shapesList.size());
        assertFalse(shapesList.getAllShapes().contains(triangle));
    }

    @Test
    void testRemoveLastShape() {
        Square square = new Square(10,20);
        Circle circle = new Circle(35,24);

        shapesList.addShape(square);
        shapesList.addShape(circle);

        shapesList.removeLastShape();
        assertEquals(1, shapesList.size());
        assertFalse(shapesList.getAllShapes().contains(circle));
    }

    @Test
    void testSize() {
        assertEquals(0, shapesList.size());

        Square shape = new Square(2,4);
        shapesList.addShape(shape);

        assertEquals(1, shapesList.size());
    }
}
