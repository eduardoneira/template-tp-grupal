package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanBeMoved;
import ar.fiuba.tdd.tp.actions.CanHaveThingsMovedFrom;
import ar.fiuba.tdd.tp.actions.CanHaveThingsMovedTo;
import ar.fiuba.tdd.tp.objects.ConcreteGameObjectContainer;
import ar.fiuba.tdd.tp.objects.GameObject;

public class Room extends ConcreteGameObjectContainer implements CanHaveThingsMovedTo, CanHaveThingsMovedFrom {

    CanHaveThingsMovedFromStrategy moveFromStrategy;
    CanHaveThingsMovedToStrategy moveToStrategy;

    public Room(String name) {
        super(name);
        moveFromStrategy = new CanHaveThingsMovedFromStrategy(this);
        moveToStrategy = new CanHaveThingsMovedToStrategy(this);
    }

    @Override
    public String haveMovedTo(CanBeMoved obj) {
        moveToStrategy.haveMovedTo(obj);
        return "There you go!";
    }

    @Override
    public String haveMovedFrom(CanBeMoved objectToMove) {
        moveFromStrategy.haveMovedFrom(objectToMove);
        return "There you go!";
    }
}
