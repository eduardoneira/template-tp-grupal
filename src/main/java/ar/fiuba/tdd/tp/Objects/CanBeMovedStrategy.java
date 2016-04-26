package ar.fiuba.tdd.tp.Objects;

import ar.fiuba.tdd.tp.Actions.CanBeMoved;
import ar.fiuba.tdd.tp.Actions.CanHaveThingsMovedTo;

/**
 * Created by Master on 26/04/2016.
 */
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
