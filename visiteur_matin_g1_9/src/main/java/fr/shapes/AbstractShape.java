package fr.shapes;

import fr.persistence.Visitable;

public abstract class AbstractShape implements Visitable, Element, SimpleShape {
    protected int x;
    protected int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
