package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanHaveThingsMovedTo;

public interface MoveToStrategy {
    String moveTo(CanHaveThingsMovedTo whereTo);
    void setParent(CanHaveThingsMovedTo parent);
}
