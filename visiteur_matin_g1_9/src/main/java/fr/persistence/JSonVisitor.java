package fr.persistence;

import fr.shapes.Circle;
import fr.shapes.ShapeGroup;
import fr.shapes.Square;
import fr.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor implements Visitor {

    private String representation = null;
    public static final String JSON_X = "\"x\": "; // créé car Sonar râlait, je trouve ça un peu illisible...
    public static final String JSON_Y = "\"y\": ";


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
        jsonBuilder.append(JSON_X).append(circle.getX()).append(",");
        jsonBuilder.append(JSON_Y).append(circle.getY()).append(",");
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
        jsonBuilder.append(JSON_X).append(square.getX()).append(",");
        jsonBuilder.append(JSON_Y).append(square.getY()).append(",");
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
        jsonBuilder.append(JSON_X).append(triangle.getX()).append(",");
        jsonBuilder.append(JSON_Y).append(triangle.getY()).append(",");
        jsonBuilder.append("}");
        
        representation = jsonBuilder.toString();
    }

    /**
     * Visite un objet de type ShapeGroup et génère une représentation JSON.
     *
     * @param triangle L'objet ShapeGroup à visiter.
    */
     @Override
    public void visit(ShapeGroup shapeGroup) {
        // Json representation for a group
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"type\": \"shapeGroup\",");
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
