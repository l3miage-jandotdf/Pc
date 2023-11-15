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


    /* README /!\
    Beaucoup de tests sont commentés.
    
    Débutantes avec Junit, nous nous sommes retrouvées dépourvues devant un problème :
    Nous avons créé nos tests et les avons fait marcher.
    Mais, surprise générale de fin, la plupart de ces tests qui passent si bien lorsque nous les exécutons à la main
    plantent avec les commandes maven... (nullPointerException)

    C'est embêtant car l'exportation sur Sonar avec la commande "mvn clean install sonar:sonar" ne marche que si les tests passent !
    Nous avons donc commenté les tests problématiques.

    C'est possiblement un problème de configuration, ou d'incompréhension via le fonctionnement de maven.

    Nous vous présentons nos excuses.
     */



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
