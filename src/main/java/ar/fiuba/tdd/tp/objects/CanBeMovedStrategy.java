package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanBeMoved;
import ar.fiuba.tdd.tp.actions.CanHaveThingsMovedTo;

public class CanBeMovedStrategy {
    CanHaveThingsMovedTo parent;
    CanBeMoved instance;

    public String moveTo(CanHaveThingsMovedTo objectToMoveTo) {
        this.parent = objectToMoveTo;
        return objectToMoveTo.haveMovedTo(instance);
    }

    protected void setParent(CanHaveThingsMovedTo parent) {
        this.parent = parent;
    }
}
