
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Graphics2D;


import org.junit.jupiter.api.Test;

import fr.commands.RemoveShape;
import fr.shapes.ShapesList;


class TestRetourArriere {
    
    @Test
    void testExecute() {

        ShapesList shapesListMock = mock(ShapesList.class);
        RemoveShape removeShape = new RemoveShape(shapesListMock);

        Graphics2D mockGraphics = mock(Graphics2D.class);
        removeShape.execute(mockGraphics);
        verify(shapesListMock).removeLastShape();
    }
}
