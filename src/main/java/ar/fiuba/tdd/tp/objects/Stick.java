package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanBeMoved;
import ar.fiuba.tdd.tp.actions.CanHaveThingsMovedTo;

public class Stick extends ConcreteGameObjectLeaf implements CanBeMoved {

    private MoveToStrategy moveToStrategy;

    public Stick(String name) {
        super(name);
        moveToStrategy = new CanBeMovedToStrategy(this);
    }

    @Override
    public String moveTo(CanHaveThingsMovedTo whereTo) {
        return moveToStrategy.moveTo(whereTo);
    }
}
