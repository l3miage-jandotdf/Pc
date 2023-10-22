import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;
import fr.JDrawingFrame;
import fr.shapes.Square;

class TestSquare {

    @Test
    void testSquareCreation() {
        int expectedX = 100;
        int expectedY = 100;

        Square square = new Square(expectedX, expectedY);

        // Vérifie si le carré est correctement positionné
        assertEquals(expectedX-25, square.getX());
        assertEquals(expectedY-25, square.getY());
    }

    //Lire le ReadMe :(
    /*
    @Test
    void testSquareExportToJson() {
        JDrawingFrame drawingFrame = new JDrawingFrame("Test");

        // Ajoute un carré à la scène
        Square square = new Square(100, 100);
        drawingFrame.addShape(square);

        // Exporte la scène au format JSON
        File file = drawingFrame.exportShapes(true);

        // Vérifie si le JSON exporté contient les informations du carré
        String jsonRepresentation = drawingFrame.readFileContent(file);
        assertTrue(jsonRepresentation.contains("\"type\": \"square\""));
        assertTrue(jsonRepresentation.contains("\"x\": 75"));
        assertTrue(jsonRepresentation.contains("\"y\": 75"));
    }


    @Test
    void testSquareExportToXml() {
        JDrawingFrame drawingFrame = new JDrawingFrame("Test");

        // Ajoute un carré
        Square square = new Square(100, 100);
        drawingFrame.addShape(square); 

        // Exporte la scène au format XML
        File file = drawingFrame.exportShapes(false);

        // Vérifie si le XML exporté contient les informations du carré
        String xmlRepresentation = drawingFrame.readFileContent(file);
        assertTrue(xmlRepresentation.contains("<type>square</type>"));
        assertTrue(xmlRepresentation.contains("<x>75</x>"));
        assertTrue(xmlRepresentation.contains("<y>75</y>"));
    }
    */
}
