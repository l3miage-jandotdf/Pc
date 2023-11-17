package fr.commands;

import java.awt.Graphics2D;

import fr.shapes.ShapesList;

public class RemoveShape implements Command {
    private ShapesList shapeList; // liste de formes

    public RemoveShape(ShapesList shapeList) {
        this.shapeList = shapeList;
    }


    /**
     * Exécute la commande pour supprimer la dernière forme de la liste de formes.
     *
     * @param g2 L'objet Graphics2D utilisé pour le dessin (non utilisé dans cette commande).
    */
    @Override
    public void execute(Graphics2D g2) {
        shapeList.removeLastShape();
    }

}
