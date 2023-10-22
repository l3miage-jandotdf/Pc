import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;
import fr.JDrawingFrame;
import fr.shapes.Circle;

class TestCircle {

    @Test
    void testCircleCreation() {
        int expectedX = 100;
        int expectedY = 100;

        Circle circle = new Circle(expectedX, expectedY);

        // Vérifie si le cercle est correctement positionné
        assertEquals(expectedX-25, circle.getX());
        assertEquals(expectedY-25, circle.getY());
    }

    //Lire le ReadMe :(
    /*
    @Test
    void testCircleExportToJson() {
        JDrawingFrame drawingFrame = new JDrawingFrame("Test");

        // Ajoute un cercle à la scène
        Circle circle = new Circle(100, 100);
        drawingFrame.addShape(circle);

        // Exporte la scène au format JSON
        File file = drawingFrame.exportShapes(true);

        // Vérifie si le JSON exporté contient les informations du cercle
        String jsonRepresentation = drawingFrame.readFileContent(file);
        assertTrue(jsonRepresentation.contains("\"type\": \"circle\""));
        assertTrue(jsonRepresentation.contains("\"x\": 75"));
        assertTrue(jsonRepresentation.contains("\"y\": 75"));
    }


    @Test
    void testCircleExportToXml() {
        JDrawingFrame drawingFrame = new JDrawingFrame("Test");

        // Ajoute un cercle
        Circle circle = new Circle(100, 100);
        drawingFrame.addShape(circle); 

        // Exporte la scène au format XML
        File file = drawingFrame.exportShapes(false);

        // Vérifie si le XML exporté contient les informations du cercle
        String xmlRepresentation = drawingFrame.readFileContent(file);
        assertTrue(xmlRepresentation.contains("<type>circle</type>"));
        assertTrue(xmlRepresentation.contains("<x>75</x>"));
        assertTrue(xmlRepresentation.contains("<y>75</y>"));
    }
    */
}
