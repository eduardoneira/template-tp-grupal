package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.*;

public abstract class ConcreteGameObject implements GameObject, CanBeLookedAt, CanBePlaced {

    private String name;

    protected MoveToStrategy moveToStrategy;

    public ConcreteGameObject(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public String lookAt() {
        return getName();
    }

    public void setParent(CanHavePlaced parent) {
        parent.place(this);
        moveToStrategy.setParent((CanHaveThingsMovedTo) parent);
    }
}
