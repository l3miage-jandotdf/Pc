package fr.persistence;

import fr.shapes.Circle;
import fr.shapes.SimpleShape;
import fr.shapes.Square;
import fr.shapes.Triangle;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class XMLVisitor implements Visitor {

    private String representation = null;

    public XMLVisitor() {
    }

    @Override
    public void visit(Circle circle) {
        // XML representation for circle
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<shape>");
        xmlBuilder.append("<type>circle</type>");
        xmlBuilder.append("<x>").append(circle.getX()).append("</x>");
        xmlBuilder.append("<y>").append(circle.getY()).append("</y>");
        xmlBuilder.append("</shape>");
        
        representation = xmlBuilder.toString();
    }

    @Override
    public void visit(Square square) {
        // XML representation for square
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<shape>");
        xmlBuilder.append("<type>square</type>");
        xmlBuilder.append("<x>").append(square.getX()).append("</x>");
        xmlBuilder.append("<y>").append(square.getY()).append("</y>");
        xmlBuilder.append("</shape>");
        
        representation = xmlBuilder.toString();
    }

    @Override
    public void visit(Triangle triangle) {
        // XML representation for square
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<shape>");
        xmlBuilder.append("<type>triangle</type>");
        xmlBuilder.append("<x>").append(triangle.getX()).append("</x>");
        xmlBuilder.append("<y>").append(triangle.getY()).append("</y>");
        xmlBuilder.append("</shape>");
        
        representation = xmlBuilder.toString();
    }

    /**
     * @return the representation in XML example for a Triangle:
     *
     *         <pre>
     * {@code
     *  <shape>
     *    <type>triangle</type>
     *    <x>-25</x>
     *    <y>-25</y>
     *  </shape>
     * }
     * </pre>
     */
    public String getRepresentation() {
        return representation;
    }
}
