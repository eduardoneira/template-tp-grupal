package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.GameObject;

public interface CanBeMoved extends GameObject, CanBePlaced {
    String moveTo(CanHaveThingsMovedTo objectToMoveTo);

}
