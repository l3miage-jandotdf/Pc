import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.event.KeyEvent;

import org.junit.jupiter.api.Test;

import fr.JDrawingFrame;
import fr.shapes.Circle;
import fr.shapes.Square;

class TestRetourArriere {
    /*
    @Test
    void testRetourArriere() {
        JDrawingFrame drawingFrame = new JDrawingFrame("Test");

        Circle circle = new Circle(100, 100);
        drawingFrame.addShape(circle);
        Square square = new Square(50, 50);
        drawingFrame.addShape(square);

        //simule un ctrl + Z
        KeyEvent keyEventCtrl = new KeyEvent(drawingFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_CONTROL, '\0');
        drawingFrame.keyPressed(keyEventCtrl);

        KeyEvent keyEventZ = new KeyEvent(drawingFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_Z, 'Z');
        drawingFrame.keyPressed(keyEventZ);

        
        int nbElements = drawingFrame.getShapeList().getAllShapes().size();
        // Compare la taille
        assertEquals(1, nbElements);
    }
    */
}
