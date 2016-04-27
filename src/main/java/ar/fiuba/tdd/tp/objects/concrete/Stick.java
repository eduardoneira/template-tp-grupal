package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.abilities.CanBeMoved;
import ar.fiuba.tdd.tp.abilities.CanHaveChildren;
import ar.fiuba.tdd.tp.abilities.CanHaveThingsMovedTo;
import ar.fiuba.tdd.tp.abilities.control.ParentControlFunctions;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeMoved;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveChildren;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveThingsMovedTo;
import ar.fiuba.tdd.tp.objects.strategies.move.CanBeMovedStrategy;

public class Stick extends ConcreteGameObject implements GameObjectCanBeMoved, ParentControlFunctions {
    private CanBeMovedStrategy beMoved;

    public Stick(String name) {
        super(name);
        beMoved = new CanBeMovedStrategy(this);
    }

    @Override
    public String moveTo(GameObjectCanHaveThingsMovedTo whereTo) {
        return beMoved.moveTo(whereTo);
    }

    @Override
    public GameObjectCanHaveChildren getParent() {
        return beMoved.getParent();
    }

    @Override
    public void setParent(GameObjectCanHaveChildren parent) {
        beMoved.setParent(parent);
    }
}
