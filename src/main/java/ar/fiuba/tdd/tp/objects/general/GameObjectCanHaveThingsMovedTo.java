package ar.fiuba.tdd.tp.objects.general;

import ar.fiuba.tdd.tp.abilities.CanHaveChildren;
import ar.fiuba.tdd.tp.abilities.CanHaveThingsMovedTo;

public interface GameObjectCanHaveThingsMovedTo extends GameObjectCanHaveChildren, GameObject, CanHaveThingsMovedTo {
    //String HaveMovedTo(GameObjectCanBeMoved objectToMove);
}
