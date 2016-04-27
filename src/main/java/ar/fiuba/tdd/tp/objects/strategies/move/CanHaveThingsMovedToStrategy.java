package ar.fiuba.tdd.tp.objects.strategies.move;

import ar.fiuba.tdd.tp.abilities.CanHaveThingsMovedTo;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeMoved;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

public class CanHaveThingsMovedToStrategy extends CanHaveThingsMovedStrategy implements CanHaveThingsMovedTo {

    public CanHaveThingsMovedToStrategy() {
        this.children = new ChildrenState();
    }

    public CanHaveThingsMovedToStrategy(ChildrenState children) {
        this.children = children;
    }

    @Override
    public String haveMovedTo(GameObjectCanBeMoved objectToMove) {
        this.children.addChild(objectToMove);
        return ""; // VER
    }

}
