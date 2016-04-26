package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanBeLookedAt;
import ar.fiuba.tdd.tp.actions.CanBeMoved;
import ar.fiuba.tdd.tp.actions.CanHaveThingsMovedTo;

public class Stick extends ConcreteGameObjectLeaf implements CanBeMoved {

    private MoveStrategy moveStrategy;

    public Stick(String name) {
        super(name);
        moveStrategy = new CanBeMovedStrategy(this);
    }

    @Override
    public String moveTo(CanHaveThingsMovedTo whereTo) {
        return moveStrategy.moveTo(whereTo);
    }
}
