import org.junit.jupiter.api.Test;

import fr.commands.ImportXML;
import fr.shapes.Element;
import fr.shapes.ShapesList;

import javax.swing.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class ImportXMLTest {

    @Test
    void testImportXMLFile() {
        
        // CrÃ©er une liste pour stocker les formes importÃ©es
        List<Element> elements = new ArrayList<>();
        ShapesList shapeList = new ShapesList();

        // CrÃ©er un JPanel (vous devez avoir une classe JPanel pour tester cela)
        JPanel panel = new JPanel();

        try {
            // Appeler la mÃ©thode d'importation XML
            ImportXML.importXMLFile(panel, elements, shapeList);

            // VÃ©rifier que la liste d'Ã©lÃ©ments n'est pas nulle et contient des Ã©lÃ©ments
            assertNotNull(shapeList);
            assertEquals(true, shapeList.size() > 0);
        } catch (IOException e) {
        }
    }
}
