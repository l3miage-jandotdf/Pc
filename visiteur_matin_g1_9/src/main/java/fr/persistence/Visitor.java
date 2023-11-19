package fr.persistence;

import fr.shapes.Circle;
import fr.shapes.ShapeGroup;
import fr.shapes.Square;
import fr.shapes.Triangle;

/**
 * You must define a method for each type of Visitable.
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public interface Visitor {

    public void visit(Square square);

    public void visit(Triangle triangle);

    public void visit(Circle circle);

    public void visit(ShapeGroup shapeGroup);

    public String getRepresentation();

    

}
