package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.abilities.CanBeMoved;
import ar.fiuba.tdd.tp.abilities.CanHaveThingsMovedFrom;
import ar.fiuba.tdd.tp.abilities.CanHaveThingsMovedTo;
import ar.fiuba.tdd.tp.abilities.CanHaveParent;
import ar.fiuba.tdd.tp.objects.general.*;
import ar.fiuba.tdd.tp.objects.strategies.move.CanHaveThingsMovedToOrFromStrategy;

public class Room extends ConcreteGameObject implements GameObjectCanHaveThingsMovedTo, GameObjectCanHaveThingsMovedFrom {

    CanHaveThingsMovedToOrFromStrategy moveToOrFromStrategy;

    public Room(String name) {
        super(name);
        moveToOrFromStrategy = new CanHaveThingsMovedToOrFromStrategy();
    }

    @Override
    public String haveMovedTo(GameObjectCanBeMoved obj) {
        moveToOrFromStrategy.haveMovedTo(obj);
        return "There you go!";
    }

    @Override
    public String haveMovedFrom(GameObjectCanBeMoved objectToMove) {
        moveToOrFromStrategy.haveMovedFrom(objectToMove);
        return "There you go!";
    }

    @Override
    public GameObjectCanHaveParent getChild(String name) {
        return moveToOrFromStrategy.getChild(name);
    }

    @Override
    public boolean contains(String name) {
        return moveToOrFromStrategy.contains(name);
    }
}
