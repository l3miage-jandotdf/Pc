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

    @Override
    public void execute(Graphics2D g2) {
        shapeList.addShape(shape);
        shape.draw(g2);
    }

    @Override
    public void goBack() {
        shapeList.removeShape(shape);
    }
}
