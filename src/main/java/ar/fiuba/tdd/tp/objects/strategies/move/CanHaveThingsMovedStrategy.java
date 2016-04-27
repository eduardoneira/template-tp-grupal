package ar.fiuba.tdd.tp.objects.strategies.move;

import ar.fiuba.tdd.tp.abilities.CanHaveChildren;
import ar.fiuba.tdd.tp.abilities.control.ChildrenControlFunctions;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveParent;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

public class CanHaveThingsMovedStrategy implements CanHaveChildren, ChildrenControlFunctions {

    protected ChildrenState children;

    public boolean contains(String name) {
        return this.children.contains(name);
    }

    public void addChild(GameObjectCanHaveParent child) {
        this.children.addChild(child);
    }

    public void removeChild(GameObjectCanHaveParent child) {
        this.children.removeChild(child);
    }

    public GameObjectCanHaveParent getChild(String name) {
        return this.children.getChild(name);
    }
}
