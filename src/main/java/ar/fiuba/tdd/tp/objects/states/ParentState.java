package ar.fiuba.tdd.tp.objects.states;

import ar.fiuba.tdd.tp.abilities.control.ParentControlFunctions;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveChildren;


public class ParentState implements ParentControlFunctions {
    private GameObject parent;

    public ParentState() {
        parent = null;
    }

    @Override
    public void setParent(GameObject parent) {
        this.parent = parent;
    }

    @Override
    public GameObject getParent() {
        return parent;
    }
}
