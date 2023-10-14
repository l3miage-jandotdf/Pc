package fr.commands;

import java.awt.Graphics2D;

import fr.shapes.ShapesList;
import fr.shapes.SimpleShape; 

public class RemoveShape implements Command {
    private ShapesList shapeList; // liste de formes

    public RemoveShape(ShapesList shapeList) {
        this.shapeList = shapeList;
    }

    @Override
    public void execute(Graphics2D g2) {
        shapeList.removeLastShape();
    }

    @Override
    public void goBack() {
    }
}
