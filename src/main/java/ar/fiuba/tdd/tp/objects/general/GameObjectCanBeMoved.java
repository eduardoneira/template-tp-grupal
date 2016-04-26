package ar.fiuba.tdd.tp.objects.general;

import ar.fiuba.tdd.tp.abilities.CanBeMoved;
import ar.fiuba.tdd.tp.abilities.CanHaveParent;

public interface GameObjectCanBeMoved extends GameObjectCanHaveParent, GameObject, CanBeMoved {
    //String moveTo(GameObjectCanHaveThingsMovedTo objectToMoveTo);
}
