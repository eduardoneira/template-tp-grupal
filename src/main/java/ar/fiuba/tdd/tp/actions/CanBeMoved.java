package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.GameObject;

public interface CanBeMoved extends GameObject {
    String moveTo(CanHaveThingsMovedTo objectToMoveTo);
}
