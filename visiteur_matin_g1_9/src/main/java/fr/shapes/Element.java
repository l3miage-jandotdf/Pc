package fr.shapes;

import fr.persistence.Visitor;

public interface Element {
    void accept(Visitor visitor);
}
