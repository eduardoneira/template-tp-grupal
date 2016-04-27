package ar.fiuba.tdd.tp.objects.strategies.move;

import ar.fiuba.tdd.tp.abilities.CanHaveThingsMovedFrom;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeMoved;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

public class CanHaveThingsMovedFromStrategy extends CanHaveThingsMovedStrategy implements CanHaveThingsMovedFrom {

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

}
