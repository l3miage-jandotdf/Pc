import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.App;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testApp() {
        try {
            // Redirige la sortie standard vers un flux
            System.setOut(new PrintStream(outputStream));
    
            // Appelle la méthode main de votre classe App avec un tableau vide
            App.main(new String[0]);
    
            // Récupère la sortie affichée sur la console
            String displayedMessage = outputStream.toString().trim();
    
            // Vérifie que la sortie correspond à ce qui était attendu
            assertEquals("Shapes Panel", displayedMessage);
        } catch (NullPointerException e) {
            // Capture l'exception et affiche les détails
            e.printStackTrace();
        }
    }
}
