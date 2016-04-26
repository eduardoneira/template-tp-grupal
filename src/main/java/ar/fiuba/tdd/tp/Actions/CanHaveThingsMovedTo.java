package ar.fiuba.tdd.tp.Actions;

import ar.fiuba.tdd.tp.Objects.GameObject;

/**
 * Created by Master on 26/04/2016.
 */
public interface CanHaveThingsMovedTo extends GameObject {
    String haveMovedTo(CanBeMoved objectToMove);
}
