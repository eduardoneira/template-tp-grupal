package ar.fiuba.tdd.tp.abilities;


import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeMoved;

public interface CanHaveThingsMovedFrom extends CanHaveChildren {
    String haveMovedFrom(GameObjectCanBeMoved objectToMove);
}
