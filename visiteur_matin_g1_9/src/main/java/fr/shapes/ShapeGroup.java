package fr.shapes;


import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import fr.persistence.Visitor;

public class ShapeGroup extends AbstractShape {

    private List<SimpleShape> shapes;

    public ShapeGroup() {
        shapes = new ArrayList<>();
    }

    /**
     * Ajoute une forme simple au groupe.
     *
     * @param shape La forme simple à ajouter au groupe.
     */
    public void addShape(SimpleShape shape) {
        shapes.add(shape);
    }

    /**
     * Supprime une forme simple du groupe.
     *
     * @param shape La forme simple à supprimer du groupe.
     */
    public void removeShape(SimpleShape shape) {
        shapes.remove(shape);
    }

    /**
     * Dessine le groupe de formes en utilisant le contexte graphique spécifié.
     *
     * @param g2 Le contexte graphique sur lequel dessiner le groupe de formes.
     */
    @Override
    public void draw(Graphics2D g2) {
        for (SimpleShape shape : shapes) {
            shape.draw(g2);
        }
    }

    /**
     * Accepte un visiteur pour le groupe de formes.
     * Cette méthode est utilisée pour permettre aux visiteurs externes d'interagir avec
     * le groupe de formes et d'effectuer des opérations spécifiques sur celui-ci.
     *
     * @param visitor L'objet Visitor qui visitera et effectuera des actions sur le groupe de formes.
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Obtient la liste des formes simples dans le groupe.
     *
     * @return La liste des formes simples dans le groupe.
     */
    public List<SimpleShape> getShapes() {
        return this.shapes;
    }
    
}
