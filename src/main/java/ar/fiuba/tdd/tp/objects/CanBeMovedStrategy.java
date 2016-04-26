package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanBeMoved;
import ar.fiuba.tdd.tp.actions.CanHaveThingsMovedTo;

public class CanBeMovedStrategy implements MoveStrategy{
    CanHaveThingsMovedTo parent;
    CanBeMoved instance;

    public String moveTo(CanHaveThingsMovedTo whereTo) {
        this.parent = whereTo;
        return whereTo.haveMovedTo(instance);
    }

    protected void setParent(CanHaveThingsMovedTo parent) {
        this.parent = parent;
    }
}
