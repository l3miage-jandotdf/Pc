package fr.commands;

import fr.shapes.SimpleShape;

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
        shapeList.addShape(shape);
        shape.draw(g2);
    }


    /**
     * Annule la commande en supprimant la forme de la liste de formes.
    */
    @Override
    public void goBack() {
        shapeList.removeShape(shape);
    }
}
