import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.shapes.Triangle;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;

class TestTriangle {

    @Mock
    private Graphics2D mockGraphics;

    private Triangle triangle;
    private int x, y;

    @Test
    void testTriangleCreation() {
        int expectedX = 100;
        int expectedY = 100;

        triangle = new Triangle(expectedX, expectedY);

        // Vérifie si le carré est correctement positionné
        assertNotNull(triangle);
        assertEquals(expectedX-25, triangle.getX());
        assertEquals(expectedY-25, triangle.getY());
    }

    @BeforeEach
    void setUp(){
        triangle = new Triangle(x, y);
        MockitoAnnotations.openMocks(this);
    }

    @Test 
    void drawTriangleTest(){
  
        Graphics2D mockGraphics = mock(Graphics2D.class);

        triangle = new Triangle(0, 0);

        triangle.draw(mockGraphics);

        verify(mockGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        verify(mockGraphics).setPaint(any(GradientPaint.class));
        verify(mockGraphics).fill(any(GeneralPath.class));
        verify(mockGraphics).setStroke(any(BasicStroke.class));
        verify(mockGraphics).setStroke(isA(BasicStroke.class));
        verify(mockGraphics).setColor(Color.black);
    }
}
