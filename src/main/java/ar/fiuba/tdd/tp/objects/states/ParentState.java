package ar.fiuba.tdd.tp.objects.states;

import ar.fiuba.tdd.tp.abilities.control.ParentControlFunctions;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;


public class ParentState implements ParentControlFunctions {
    private GameObjectWithChildren parent;

    public ParentState() {
        parent = null;
    }

    @Override
    public void setParent(GameObjectWithChildren parent) {
        this.parent = parent;
    }

    @Override
    public GameObjectWithChildren getParent() {
        return parent;
    }
}
