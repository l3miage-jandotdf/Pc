import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

import fr.JDrawingFrame;
import fr.shapes.Circle;
import fr.shapes.Square;
import fr.shapes.Triangle;

class TestExportXml {
/* 
    @Test
    void testCircleExportToXml() {
        JDrawingFrame drawingFrame = new JDrawingFrame("Test");

        Circle circle = new Circle(100, 100);
        Square square = new Square(25, 25);
        Triangle triangle = new Triangle(50,60);
        drawingFrame.addShape(circle);
        drawingFrame.addShape(square);
        drawingFrame.addShape(triangle);


        // Exporte la scène au format xml
        File file = drawingFrame.exportShapes(false);

        // Vérifie si le .xml exporté contient les informations des formes
        String xmlRepresentation = drawingFrame.readFileContent(file);
        assertTrue(xmlRepresentation.contains("<type>circle</type>"));
        assertTrue(xmlRepresentation.contains("<x>75</x>"));
        assertTrue(xmlRepresentation.contains("<y>75</y>"));
        assertTrue(xmlRepresentation.contains("<type>square</type>"));
        assertTrue(xmlRepresentation.contains("<x>0</x>"));
        assertTrue(xmlRepresentation.contains("<y>0</y>"));
        assertTrue(xmlRepresentation.contains("<type>triangle</type>"));
        assertTrue(xmlRepresentation.contains("<x>25</x>"));
        assertTrue(xmlRepresentation.contains("<y>35</y>"));
    }
    
     */
}
