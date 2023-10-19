package fr.shapes;
import java.util.ArrayList;
import java.util.List;


public class ShapesList {

    private List<SimpleShape> shapes;

    public ShapesList() {
        shapes = new ArrayList<>();
    }

    /**
     * Ajoute une forme simple à la liste.
     *
     * @param shape La forme simple à ajouter.
    */
    public void addShape(SimpleShape shape) {
        shapes.add(shape);
    }

    /**
     * Supprime une forme simple de la liste.
     *
     * @param shape La forme simple à supprimer.
    */
    public void removeShape(SimpleShape shape) {
        shapes.remove(shape);
    }

    /**
     * Obtient la liste de toutes les formes simples.
     *
     * @return La liste de formes simples.
    */
    public List<SimpleShape> getAllShapes() {
        return shapes;
    }

    /**
     * Supprime la dernière forme de la liste, si la liste n'est pas vide.
    */
    public void removeLastShape() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1);
        }
    }
}
