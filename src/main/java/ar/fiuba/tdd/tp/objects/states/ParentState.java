package ar.fiuba.tdd.tp.objects.states;

import ar.fiuba.tdd.tp.abilities.CanHaveParentPrivate;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveChildren;

public class ParentState implements CanHaveParentPrivate {
    private GameObjectCanHaveChildren parent;

    public ParentState() {
        parent = null;
    }

    @Override
    public void setParent(GameObjectCanHaveChildren parent) {
        this.parent = parent;
    }

    @Override
    public GameObjectCanHaveChildren getParent() {
        return parent;
    }
}
