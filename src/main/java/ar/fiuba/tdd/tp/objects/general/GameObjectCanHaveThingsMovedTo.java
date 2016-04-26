package ar.fiuba.tdd.tp.objects.general;

import ar.fiuba.tdd.tp.abilities.CanHaveThingsMovedTo;
import ar.fiuba.tdd.tp.abilities.CanHaveChildren;

public interface GameObjectCanHaveThingsMovedTo extends GameObjectCanHaveChildren, GameObject, CanHaveThingsMovedTo {
    //String haveMovedTo(GameObjectCanBeMoved objectToMove);
}
