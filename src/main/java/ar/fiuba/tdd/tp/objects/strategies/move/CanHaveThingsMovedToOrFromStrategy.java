package ar.fiuba.tdd.tp.objects.strategies.move;

import ar.fiuba.tdd.tp.abilities.CanBeMoved;
import ar.fiuba.tdd.tp.abilities.CanHaveParent;
import ar.fiuba.tdd.tp.abilities.CanHaveThingsMovedFrom;
import ar.fiuba.tdd.tp.abilities.CanHaveThingsMovedTo;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeMoved;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveParent;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

/**
 * Created by Master on 26/04/2016.
 */
public class CanHaveThingsMovedToOrFromStrategy implements CanHaveThingsMovedFrom, CanHaveThingsMovedTo {
    private CanHaveThingsMovedToStrategy moveTo;
    private CanHaveThingsMovedFrom moveFrom;

    public CanHaveThingsMovedToOrFromStrategy() {
        ChildrenState children = new ChildrenState();
        moveTo = new CanHaveThingsMovedToStrategy(children);
        moveFrom = new CanHaveThingsMovedFromStrategy(children);
    }

    public CanHaveThingsMovedToOrFromStrategy(ChildrenState children) {
        moveTo = new CanHaveThingsMovedToStrategy(children);
        moveFrom = new CanHaveThingsMovedFromStrategy(children);
    }


    @Override
    public String haveMovedFrom(GameObjectCanBeMoved objectToMove) {
        return moveFrom.haveMovedFrom(objectToMove);
    }

    @Override
    public String haveMovedTo(GameObjectCanBeMoved objectToMove) {
        return moveTo.haveMovedTo(objectToMove);
    }

    @Override
    public GameObjectCanHaveParent getChild(String name) {
        return moveTo.getChild(name);
    }

    @Override
    public boolean contains(String name) {
        return moveTo.contains(name);
    }
}
