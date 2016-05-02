package ar.fiuba.tdd.tp.objects.general;

import ar.fiuba.tdd.tp.abilities.control.ChildrenControlFunctions;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.List;

public class ConcreteGameObjectWithChildren extends ConcreteGameObject implements ChildrenControlFunctions, GameObjectWithChildren {

    protected ChildrenState children;

    public ConcreteGameObjectWithChildren(String name) {
        super(name);
        this.children = new ChildrenState();
    }

    @Override
    public void addChild(GameObjectWithParent child) {
        children.addChild(child);
    }

    @Override
    public void removeChild(GameObjectWithParent child) {
        children.removeChild(child);
    }

    @Override
    public boolean isEmpty() {
        return children.isEmpty();
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
}
