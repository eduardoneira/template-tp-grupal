package ar.fiuba.tdd.tp.objects.general;

import ar.fiuba.tdd.tp.abilities.control.ChildrenControlFunctions;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

/**
 * Created by Master on 27/04/2016.
 */
public class ConcreteGameObjectWithChildren extends ConcreteGameObject implements ChildrenControlFunctions {

    protected ChildrenState children;

    public ConcreteGameObjectWithChildren(String name) {
        super(name);
        this.children = new ChildrenState();
    }

    @Override
    public void addChild(GameObject child) {
        children.addChild(child);
    }

    @Override
    public void removeChild(GameObject child) {
        children.removeChild(child);
    }

    @Override
    public GameObject getChild(String name) {
        return children.getChild(name);
    }

    @Override
    public boolean contains(String name) {
        return children.contains(name);
    }
}
