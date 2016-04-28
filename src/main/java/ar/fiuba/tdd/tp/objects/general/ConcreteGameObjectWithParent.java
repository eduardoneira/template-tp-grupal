package ar.fiuba.tdd.tp.objects.general;

import ar.fiuba.tdd.tp.abilities.control.ParentControlFunctions;
import ar.fiuba.tdd.tp.objects.states.ParentState;

public class ConcreteGameObjectWithParent extends ConcreteGameObject implements ParentControlFunctions {

    protected ParentState parent;

    public ConcreteGameObjectWithParent(String name) {
        super(name);
        parent = new ParentState();
    }

    @Override
    public void setParent(GameObject parent) {
        this.parent.setParent(parent);
    }

    @Override
    public GameObject getParent() {
        return this.parent.getParent();
    }
}
