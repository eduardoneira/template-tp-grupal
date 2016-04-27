package ar.fiuba.tdd.tp.objects.states;

import ar.fiuba.tdd.tp.abilities.control.ParentControlFunctions;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveChildren;


public class ParentState implements ParentControlFunctions {
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
