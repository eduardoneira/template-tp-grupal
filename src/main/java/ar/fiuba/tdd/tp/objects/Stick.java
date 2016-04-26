package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanBeLookedAt;
import ar.fiuba.tdd.tp.actions.CanBeMoved;
import ar.fiuba.tdd.tp.actions.CanHaveThingsMovedTo;

public class Stick extends ConcreteGameObjectLeaf implements CanBeMoved, CanBeLookedAt {

    private MoveStrategy movedStrategy;

    public Stick(String name) {
        super(name);
        movedStrategy = new CanBeMovedStrategy();
    }

    public String moveTo(CanHaveThingsMovedTo whereTo) {
        return movedStrategy.moveTo(whereTo);
    }
}
