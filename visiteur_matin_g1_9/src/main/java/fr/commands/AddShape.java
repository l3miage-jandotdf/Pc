package fr.commands;

import fr.shapes.SimpleShape;

import java.awt.Color;
import java.awt.Graphics2D;

import fr.shapes.ShapesList; // Suppose que vous avez une classe ShapeList pour stocker les formes

public class AddShape implements Command {
    private ShapesList shapeList; // liste de formes
    private SimpleShape shape; // forme à ajouter


    public AddShape(ShapesList shapeList, SimpleShape shape) {
        this.shapeList = shapeList;
        this.shape = shape;
    }

    /**
     * Exécute la commande pour ajouter la forme à la liste de formes et la dessine.
     *
     * @param g2 L'objet Graphics2D utilisé pour le dessin.
    */
    @Override
    public void execute(Graphics2D g2) {

        if (shapeList.isGroupMode() && shapeList.getCurrentGroup() != null) {
            g2.setColor(Color.YELLOW); // Définissez la couleur du contour sur jaune pour les formes de groupe
        }

        shapeList.addShape(shape);
        shape.draw(g2);

        if (shapeList.isGroupMode() && shapeList.getCurrentGroup() != null) {
            g2.setColor(Color.BLACK); // Rétablissez la couleur du contour à sa valeur par défaut après avoir dessiné la forme
        }
    }

}
