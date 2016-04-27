package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.abilities.control.ParentControlFunctions;
import ar.fiuba.tdd.tp.objects.general.*;
import ar.fiuba.tdd.tp.objects.states.ParentState;
import ar.fiuba.tdd.tp.objects.strategies.move.CanHaveThingsMovedToOrFromStrategy;

public class Box extends ConcreteGameObject implements GameObjectCanHaveThingsMovedFrom, GameObjectCanHaveThingsMovedTo,
        ParentControlFunctions, GameObjectCanHaveParent {

    private CanHaveThingsMovedToOrFromStrategy moveStrategy;
    ParentState parent;

    public Box(String name) {
        super(name);
        moveStrategy = new CanHaveThingsMovedToOrFromStrategy();
        parent = new ParentState();
    }

    @Override
    public String haveMovedTo(GameObjectCanBeMoved objectToMove) {
        return moveStrategy.haveMovedTo(objectToMove);
    }

    @Override
    public String haveMovedFrom(GameObjectCanBeMoved objectToMove) {
        return moveStrategy.haveMovedFrom(objectToMove);
    }

    @Override
    public GameObjectCanHaveParent getChild(String name) {
        return moveStrategy.getChild(name);
    }

    @Override
    public boolean contains(String name) {
        return moveStrategy.contains(name);
    }

    @Override
    public void setParent(GameObjectCanHaveChildren parent) {
        this.parent.setParent(parent);
    }

    @Override
    public GameObjectCanHaveChildren getParent() {
        return this.parent.getParent();
    }

}
