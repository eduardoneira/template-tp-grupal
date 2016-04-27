package ar.fiuba.tdd.tp.objects.general;

import ar.fiuba.tdd.tp.abilities.control.ChildrenControlFunctions;
import ar.fiuba.tdd.tp.abilities.control.ParentControlFunctions;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;
import ar.fiuba.tdd.tp.objects.states.ParentState;

import java.util.List;

public class ConcreteGameObjectWithParentAndChildren extends ConcreteGameObject implements ParentControlFunctions, ChildrenControlFunctions {

    protected ChildrenState children;
    protected ParentState parent;

    public ConcreteGameObjectWithParentAndChildren(String name) {
        super(name);
        children = new ChildrenState();
        parent = new ParentState();
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

    @Override
    public List<GameObject> getChildren() {
        return children.getChildren();
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
