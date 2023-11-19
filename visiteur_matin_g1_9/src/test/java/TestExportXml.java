import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.persistence.XMLVisitor;
import fr.shapes.Circle;
import fr.shapes.Square;
import fr.shapes.Triangle;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestExportXml {

    @Mock
    Circle circle;

    @Mock
    Square square;

    @Mock
    Triangle triangle;

    private XMLVisitor xmlVisitor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        xmlVisitor = new XMLVisitor();
    }


    @Test
    void circleXmlExportTest() {

        assertNotNull(circle);

        //Définit les valeurs des coordonnées attendues
        when(circle.getX()).thenReturn(5);
        when(circle.getY()).thenReturn(20);

        xmlVisitor.visit(circle);
        String xml = xmlVisitor.getRepresentation();

        // Le format xml attendu
        assertTrue(xml.contains("<type>circle</type>"));
        assertTrue(xml.contains("<x>5</x>"));
        assertTrue(xml.contains("<y>20</y>"));;
    }

    @Test
    void squareXmlExportTest() {

        assertNotNull(square);

        //Définit les valeurs des coordonnées attendues
        when(square.getX()).thenReturn(32);
        when(square.getY()).thenReturn(18);


        xmlVisitor.visit(square);
        String xml = xmlVisitor.getRepresentation();

        // Le format xml attendu
        assertTrue(xml.contains("<type>square</type>"));
        assertTrue(xml.contains("<x>32</x>"));
        assertTrue(xml.contains("<y>18</y>"));
    }
    

    @Test
    void triangleXmlExportTest() {

        assertNotNull(triangle);

        //Définit les valeurs des coordonnées attendues
        when(triangle.getX()).thenReturn(20);
        when(triangle.getY()).thenReturn(20);

        xmlVisitor.visit(triangle);
        String xml = xmlVisitor.getRepresentation();

        // Le format xml attendu
        assertTrue(xml.contains("<type>triangle</type>"));
        assertTrue(xml.contains("<x>20</x>"));
        assertTrue(xml.contains("<y>20</y>"));
    }

}
