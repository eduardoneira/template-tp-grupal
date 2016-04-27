package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.abilities.control.ChildrenControlFunctions;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeMoved;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveParent;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveThingsMovedTo;
import ar.fiuba.tdd.tp.objects.strategies.move.CanHaveThingsMovedToStrategy;

public class Inventory extends ConcreteGameObject implements GameObjectCanHaveThingsMovedTo, ChildrenControlFunctions {
    // tal vez deberia ser to and from, por ahora esta bien asi
    private CanHaveThingsMovedToStrategy mover;

    public Inventory(String name) {
        super(name);
        mover = new CanHaveThingsMovedToStrategy();
    }

    @Override
    public String haveMovedTo(GameObjectCanBeMoved objectToMove) {
        return mover.haveMovedTo(objectToMove);
    }

    @Override
    public GameObjectCanHaveParent getChild(String name) {
        return mover.getChild(name);
    }

    @Override
    public boolean contains(String name) {
        return mover.contains(name);
    }

    @Override
    public void addChild(GameObjectCanHaveParent child) {
        mover.addChild(child);
    }

    @Override
    public void removeChild(GameObjectCanHaveParent child) {
        mover.removeChild(child);
    }
}
