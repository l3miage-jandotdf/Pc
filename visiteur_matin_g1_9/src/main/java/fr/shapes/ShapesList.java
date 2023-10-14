package fr.shapes;
import java.util.ArrayList;
import java.util.List;


public class ShapesList {

    private List<SimpleShape> shapes;

    public ShapesList() {
        shapes = new ArrayList<>();
    }

    public void addShape(SimpleShape shape) {
        shapes.add(shape);
    }

    public void removeShape(SimpleShape shape) {
        shapes.remove(shape);
    }

    public List<SimpleShape> getAllShapes() {
        return shapes;
    }

    public void removeLastShape() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
        }
    }
}
