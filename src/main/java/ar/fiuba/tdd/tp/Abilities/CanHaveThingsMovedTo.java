package ar.fiuba.tdd.tp.abilities;

import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeMoved;

public interface CanHaveThingsMovedTo extends CanHaveChildren {
    String haveMovedTo(GameObjectCanBeMoved objectToMove);
}
