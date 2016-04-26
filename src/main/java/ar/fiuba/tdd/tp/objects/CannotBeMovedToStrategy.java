package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanBeMoved;
import ar.fiuba.tdd.tp.actions.CanHaveThingsMovedTo;

public class CannotBeMovedToStrategy implements MoveToStrategy {
    CanHaveThingsMovedTo parent;
    CanBeMoved instance;

    public CannotBeMovedToStrategy() {

    }

    public String moveTo(CanHaveThingsMovedTo whereTo) {
        return "Cannot be moved!";
    }

    public void setParent(CanHaveThingsMovedTo parent) {
        this.parent = parent;
    }
}