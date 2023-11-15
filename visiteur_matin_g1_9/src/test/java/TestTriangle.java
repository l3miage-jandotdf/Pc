import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;
import fr.JDrawingFrame;
import fr.shapes.Triangle;

class TestTriangle {

    @Test
    void testTriangleCreation() {
        int expectedX = 100;
        int expectedY = 100;

        Triangle triangle = new Triangle(expectedX, expectedY);

        // Vérifie si le triangle est correctement positionné
        assertEquals(expectedX-25, triangle.getX());
        assertEquals(expectedY-25, triangle.getY());
    }

    /*
    @Test
    void testTriangleExportToJson() {
        JDrawingFrame drawingFrame = new JDrawingFrame("Test");

        // Ajoute un triangle à la scène
        Triangle triangle = new Triangle(100, 100);
        drawingFrame.addShape(triangle);

        // Exporte la scène au format JSON
        File file = drawingFrame.exportShapes(true);

        // Vérifie si le JSON exporté contient les informations du triangle
        String jsonRepresentation = drawingFrame.readFileContent(file);
        assertTrue(jsonRepresentation.contains("\"type\": \"triangle\""));
        assertTrue(jsonRepresentation.contains("\"x\": 75"));
        assertTrue(jsonRepresentation.contains("\"y\": 75"));
    }


    @Test
    void testTriangleExportToXml() {
        JDrawingFrame drawingFrame = new JDrawingFrame("Test");

        // Ajoute un triangle
        Triangle triangle = new Triangle(100, 100);
        drawingFrame.addShape(triangle); 

        // Exporte la scène au format XML
        File file = drawingFrame.exportShapes(false);

        // Vérifie si le XML exporté contient les informations du triangle
        String xmlRepresentation = drawingFrame.readFileContent(file);
        assertTrue(xmlRepresentation.contains("<type>triangle</type>"));
        assertTrue(xmlRepresentation.contains("<x>75</x>"));
        assertTrue(xmlRepresentation.contains("<y>75</y>"));
    }
    */
}
