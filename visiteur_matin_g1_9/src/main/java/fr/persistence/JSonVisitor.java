package fr.persistence;

import fr.shapes.Circle;
import fr.shapes.Square;
import fr.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor implements Visitor {

    private String representation = null;

    public JSonVisitor() {
    }


    /**
     * Visite un objet de type Circle et génère une représentation JSON.
     *
     * @param circle L'objet Circle à visiter.
    */
    @Override
    public void visit(Circle circle) {
        // Json representation for circle
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"type\": \"circle\",");
        jsonBuilder.append("\"x\": ").append(circle.getX()).append(",");
        jsonBuilder.append("\"y\": ").append(circle.getY());
        jsonBuilder.append("}");
        
        representation = jsonBuilder.toString();
    }

    /**
     * Visite un objet de type Square et génère une représentation JSON.
     *
     * @param square L'objet Square à visiter.
    */
    @Override
    public void visit(Square square) {
        // Json representation for square
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"type\": \"square\",");
        jsonBuilder.append("\"x\": ").append(square.getX()).append(",");
        jsonBuilder.append("\"y\": ").append(square.getY());
        jsonBuilder.append("}");

        representation = jsonBuilder.toString();
    }

     /**
     * Visite un objet de type Triangle et génère une représentation JSON.
     *
     * @param triangle L'objet Triangle à visiter.
    */
    @Override
    public void visit(Triangle triangle) {
        // Json representation for triangle
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"type\": \"triangle\",");
        jsonBuilder.append("\"x\": ").append(triangle.getX()).append(",");
        jsonBuilder.append("\"y\": ").append(triangle.getY());
        jsonBuilder.append("}");
        
        representation = jsonBuilder.toString();
    }

    /**
     * @return the representation in JSon example for a Circle
     *
     *         <pre>
     * {@code
     *  {
     *     "shape": {
     *     	  "type": "circle",
     *        "x": -25,
     *        "y": -25
     *     }
     *  }
     * }
     *         </pre>
     */
    public String getRepresentation() {
        return representation;
    }
}
