package ar.fiuba.tdd.tp.abilities;

import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveThingsMovedTo;

public interface CanBeMoved extends CanHaveParent {
    String moveTo(GameObjectCanHaveThingsMovedTo objectToMoveTo);
}
