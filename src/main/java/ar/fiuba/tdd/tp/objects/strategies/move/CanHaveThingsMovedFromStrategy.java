package ar.fiuba.tdd.tp.objects.strategies.move;

import ar.fiuba.tdd.tp.abilities.control.ChildrenControlFunctions;
import ar.fiuba.tdd.tp.abilities.CanHaveThingsMovedFrom;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeMoved;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveParent;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

public class CanHaveThingsMovedFromStrategy implements CanHaveThingsMovedFrom, ChildrenControlFunctions {
    ChildrenState children;

    public CanHaveThingsMovedFromStrategy() {
        this.children = new ChildrenState();
    }

    public CanHaveThingsMovedFromStrategy(ChildrenState children) {
        this.children = children;
    }

    @Override
    public String haveMovedFrom(GameObjectCanBeMoved objectToMove) {
        children.removeChild(objectToMove);
        return ""; // VER
    }

    @Override
    public GameObjectCanHaveParent getChild(String name) {
        return children.getChild(name);
    }

    @Override
    public boolean contains(String name) {
        return this.children.contains(name);
    }

    @Override
    public void addChild(GameObjectCanHaveParent child) {
        this.children.addChild(child);
    }

    @Override
    public void removeChild(GameObjectCanHaveParent child) {
        this.children.removeChild(child);
    }
}
