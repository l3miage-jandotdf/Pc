import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.shapes.Square;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;


class TestSquare {

    @Mock
    private Graphics2D mockGraphics;

    private Square square;
    private int x, y;

    @Test
    void testSquareCreation() {
        int expectedX = 100;
        int expectedY = 100;

        square = new Square(expectedX, expectedY);

        // Vérifie si le carré est correctement positionné
        assertNotNull(square);
        assertEquals(expectedX-25, square.getX());
        assertEquals(expectedY-25, square.getY());
    }

    @BeforeEach
    void setUp(){
        square = new Square(x, y);
        MockitoAnnotations.openMocks(this);
    }

    @Test 
    void drawSquareTest(){

        Graphics2D mockGraphics = mock(Graphics2D.class);

        square = new Square(0, 0);

        square.draw(mockGraphics);

        verify(mockGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        verify(mockGraphics).setPaint(any(GradientPaint.class));
        verify(mockGraphics).fill(any(Rectangle2D.Double.class));
        verify(mockGraphics).draw(any(Rectangle2D.Double.class));
        verify(mockGraphics).setStroke(any(BasicStroke.class));
        verify(mockGraphics).setColor(Color.black);
    }
    
}
