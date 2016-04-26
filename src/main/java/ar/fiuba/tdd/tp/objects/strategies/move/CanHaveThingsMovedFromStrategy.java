package ar.fiuba.tdd.tp.objects.strategies.move;

import ar.fiuba.tdd.tp.abilities.CanBeMoved;
import ar.fiuba.tdd.tp.abilities.CanHaveThingsMovedFrom;
import ar.fiuba.tdd.tp.abilities.CanHaveParent;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeMoved;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveParent;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

public class CanHaveThingsMovedFromStrategy implements CanHaveThingsMovedFrom {
    ChildrenState children;

    public CanHaveThingsMovedFromStrategy(){
        this.children = new ChildrenState();
    }

    public CanHaveThingsMovedFromStrategy(ChildrenState children){
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
        return children.contains(name);
    }
}
