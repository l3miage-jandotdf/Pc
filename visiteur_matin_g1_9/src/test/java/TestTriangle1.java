import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.event.MouseEvent;

import org.junit.jupiter.api.Test;
import fr.JDrawingFrame;
import fr.shapes.Triangle;
import fr.persistence.JSonVisitor;
import fr.persistence.XMLVisitor;

public class TestTriangle {

    @Test
    public void testTrianglePosition() {
        // Crée une instance de JDrawingFrame (ou utilisez votre classe principale)
        JDrawingFrame drawingFrame = new JDrawingFrame("Test");

        // Coordonnées supposées du triangle
        int expectedX = 100;
        int expectedY = 100;

        // Ajoute un triangle à la scène
        Triangle triangle = new Triangle(expectedX, expectedY);
        drawingFrame.mouseClicked(new MouseEvent(drawingFrame, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 100, 100, 1, false));

        // Vérifie si le triangle est correctement positionné
        assertEquals(expectedX-25, triangle.getX());
        assertEquals(expectedY-25, triangle.getY());
    }

    @Test
    public void testTriangleExportToJson() {
        // Crée une instance de JDrawingFrame (ou utilisez votre classe principale)
        JDrawingFrame drawingFrame = new JDrawingFrame("Test");

        // Ajoute un triangle à la scène
        Triangle triangle = new Triangle(100, 100);
        drawingFrame.mouseClicked(new MouseEvent(drawingFrame, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 100, 100, 1, false));

        // Exporte la scène au format JSON
        JSonVisitor jsonVisitor = new JSonVisitor();
        drawingFrame.exportShapesToJSON();

        // Vérifie si le JSON exporté contient les informations du triangle
        String jsonRepresentation = jsonVisitor.getRepresentation();
        assertTrue(jsonRepresentation.contains("\"type\":\"triangle\""));
        assertTrue(jsonRepresentation.contains("\"x\":100"));
        assertTrue(jsonRepresentation.contains("\"y\":100"));
    }

    @Test
    public void testTriangleExportToXml() {
        // Crée une instance de JDrawingFrame (ou utilisez votre classe principale)
        JDrawingFrame drawingFrame = new JDrawingFrame("Test");

        // Ajoute un triangle à la scène
        Triangle triangle = new Triangle(100, 100);
        //drawingFrame.addShape(triangle);

        // Exporte la scène au format XML
        XMLVisitor xmlVisitor = new XMLVisitor();
        //drawingFrame.exportShapesToXML();

        // Vérifie si le XML exporté contient les informations du triangle
        String xmlRepresentation = xmlVisitor.getRepresentation();
        System.out.println("-----------------------------------");
        System.out.println(xmlRepresentation);
        //assertTrue(xmlRepresentation.contains("<type>triangle</type>"));
        //assertTrue(xmlRepresentation.contains("<x>100</x>"));
        //assertTrue(xmlRepresentation.contains("<y>100</y>"));
    }
}
