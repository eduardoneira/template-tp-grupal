package ar.fiuba.tdd.tp.objects.strategies.move;

import ar.fiuba.tdd.tp.abilities.CanBeMoved;
import ar.fiuba.tdd.tp.abilities.CanHaveThingsMovedTo;
import ar.fiuba.tdd.tp.abilities.CanHaveParent;
import ar.fiuba.tdd.tp.abilities.control.ChildrenControlFunctions;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeMoved;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveParent;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

public class CanHaveThingsMovedToStrategy implements CanHaveThingsMovedTo, ChildrenControlFunctions {
    ChildrenState children;

    public CanHaveThingsMovedToStrategy(){
        this.children = new ChildrenState();
    }

    public CanHaveThingsMovedToStrategy(ChildrenState children){
        this.children = children;
    }

    @Override
    public String haveMovedTo(GameObjectCanBeMoved objectToMove) {
        this.children.addChild(objectToMove);
        return ""; // VER
    }

    @Override
    public GameObjectCanHaveParent getChild(String name) {
        return this.children.getChild(name);
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
