package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanBeLookedAt;
import ar.fiuba.tdd.tp.actions.CanBePlaced;
import ar.fiuba.tdd.tp.actions.CanHavePlaced;
import ar.fiuba.tdd.tp.actions.CanHaveThingsMovedTo;

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
    }
}
