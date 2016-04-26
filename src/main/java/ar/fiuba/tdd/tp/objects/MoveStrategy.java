package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanHaveThingsMovedTo;

public interface MoveStrategy {
    public String moveTo(CanHaveThingsMovedTo whereTo);
}
