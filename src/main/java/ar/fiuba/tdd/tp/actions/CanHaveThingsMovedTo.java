package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.GameObject;

public interface CanHaveThingsMovedTo extends GameObject {
    String haveMovedTo(CanBeMoved objectToMove);
}
