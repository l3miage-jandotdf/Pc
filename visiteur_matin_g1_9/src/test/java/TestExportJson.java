import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

import fr.JDrawingFrame;
import fr.shapes.Circle;
import fr.shapes.Square;
import fr.shapes.Triangle;

public class TestExportJson {
    /* 
    @Test
    void testCircleExportToJson() {
        JDrawingFrame drawingFrame = new JDrawingFrame("Test");

        Circle circle = new Circle(100, 100);
        Square square = new Square(25, 25);
        Triangle triangle = new Triangle(50,60);
        drawingFrame.addShape(circle);
        drawingFrame.addShape(square);
        drawingFrame.addShape(triangle);


        // Exporte la scène au format JSON
        File file = drawingFrame.exportShapes(true);

        // Vérifie si le JSON exporté contient les informations des formes
        String jsonRepresentation = drawingFrame.readFileContent(file);
        assertTrue(jsonRepresentation.contains("\"type\": \"circle\""));
        assertTrue(jsonRepresentation.contains("\"x\": 75"));
        assertTrue(jsonRepresentation.contains("\"y\": 75"));
        assertTrue(jsonRepresentation.contains("\"type\": \"square\""));
        assertTrue(jsonRepresentation.contains("\"x\": 0"));
        assertTrue(jsonRepresentation.contains("\"y\": 0"));
        assertTrue(jsonRepresentation.contains("\"type\": \"triangle\""));
        assertTrue(jsonRepresentation.contains("\"x\": 25"));
        assertTrue(jsonRepresentation.contains("\"y\": 35"));
    }
    */
}
