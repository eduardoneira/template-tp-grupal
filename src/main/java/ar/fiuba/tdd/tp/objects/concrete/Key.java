package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.abilities.control.ParentControlFunctions;
import ar.fiuba.tdd.tp.objects.general.*;
import ar.fiuba.tdd.tp.objects.strategies.move.CanBeMovedStrategy;

public class Key extends ConcreteGameObject implements GameObjectCanBeMoved, ParentControlFunctions {
    CanBeMovedStrategy beMoved;

    public Key(String name) {
        super(name);
        beMoved = new CanBeMovedStrategy(this);
    }

    @Override
    public String moveTo(GameObjectCanHaveThingsMovedTo objectToMoveTo) {
        return beMoved.moveTo(objectToMoveTo);
    }

    @Override
    public void setParent(GameObjectCanHaveChildren parent) {
        beMoved.setParent(parent);
    }

    @Override
    public GameObjectCanHaveChildren getParent() {
        return beMoved.getParent();
    }
}
