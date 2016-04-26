package ar.fiuba.tdd.tp.objects.general;

import ar.fiuba.tdd.tp.abilities.CanHaveThingsMovedFrom;
import ar.fiuba.tdd.tp.abilities.CanHaveChildren;

/**
 * Created by Master on 26/04/2016.
 */
public interface GameObjectCanHaveThingsMovedFrom extends GameObjectCanHaveChildren, GameObject, CanHaveThingsMovedFrom {
    //String haveMovedFrom(GameObjectCanBeMoved objectToMove);
}
