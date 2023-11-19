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

import fr.shapes.Circle;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.isA;

class TestCircle {

    @Mock
    private Graphics2D mockGraphics;

    private Circle circle;
    private int x, y;
    
    
    @BeforeEach
    void setUp(){
        circle = new Circle(x, y);
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCircleCreation() {
        int expectedX = 100;
        int expectedY = 100;

        circle = new Circle(expectedX, expectedY);

        // Vérifie si le cercle est correctement positionné
        assertNotNull(circle);
        assertEquals(expectedX-25, circle.getX());
        assertEquals(expectedY-25, circle.getY());
    }

     @Test 
    void drawCircleTest(){
  
        Graphics2D mockGraphics = mock(Graphics2D.class);

        circle = new Circle(0, 0);

        circle.draw(mockGraphics);

        verify(mockGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        verify(mockGraphics).setPaint(isA(GradientPaint.class));
        verify(mockGraphics).fill(isA(Rectangle2D.Double.class));
        verify(mockGraphics).draw(isA(Rectangle2D.Double.class));
        verify(mockGraphics).setStroke(isA(BasicStroke.class));
        verify(mockGraphics).setColor(Color.black);
    }
}
