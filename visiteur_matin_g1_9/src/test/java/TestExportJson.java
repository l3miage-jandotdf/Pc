import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.persistence.JSonVisitor;
import fr.shapes.Circle;
import fr.shapes.Square;
import fr.shapes.Triangle;

class TestExportJson {
    @Mock
    Circle circle;

    @Mock
    Square square;

    @Mock
    Triangle triangle;

    private JSonVisitor jSonVisitor;

     @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        jSonVisitor = new JSonVisitor();
    }

 
    @Test
    void testCircleExportToJson() {

        assertNotNull(circle);

        when(circle.getX()).thenReturn(10);
        when(circle.getY()).thenReturn(12);

        jSonVisitor.visit(circle);

        // Vérifiez que la représentation JSON générée est correcte
        assertEquals("{\"type\": \"circle\",\"x\": 10,\"y\": 12,}", jSonVisitor.getRepresentation());
    }


    @Test
    void testSquareExportToJson() {

        assertNotNull(square);

        when(square.getX()).thenReturn(20);
        when(square.getY()).thenReturn(-15);

        jSonVisitor.visit(square);

        // Vérifiez que la représentation JSON générée est correcte
        assertEquals("{\"type\": \"square\",\"x\": 20,\"y\": -15,}", jSonVisitor.getRepresentation());
    }

    @Test
    void testTriangleExportToJson() {

        assertNotNull(triangle);

        when(triangle.getX()).thenReturn(30);
        when(triangle.getY()).thenReturn(30);

        jSonVisitor.visit(triangle);

        // Vérifiez que la représentation JSON générée est correcte
        assertEquals("{\"type\": \"triangle\",\"x\": 30,\"y\": 30,}", jSonVisitor.getRepresentation());
    }
}
