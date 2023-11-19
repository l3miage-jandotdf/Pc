import fr.commands.AddShape;
import fr.shapes.ShapesList;
import fr.shapes.SimpleShape;
import org.junit.jupiter.api.Test;

import java.awt.Graphics2D;

import static org.mockito.Mockito.*;

class AddShapeTest {

    @Test
    void testExecute() {

        ShapesList shapesListMock = mock(ShapesList.class);
        SimpleShape simpleShapeMock = mock(SimpleShape.class);

        AddShape addShape = new AddShape(shapesListMock, simpleShapeMock);


        Graphics2D mockGraphics = mock(Graphics2D.class);

 
        addShape.execute(mockGraphics);

        // Vérifiez que la méthode addShape est appelée avec l'objet SimpleShape
        verify(shapesListMock).addShape(simpleShapeMock);

        // Vérifiez que la méthode draw de SimpleShape est appelée avec l'objet Graphics2D
        verify(simpleShapeMock).draw(mockGraphics);
    }
}

