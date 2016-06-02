package ar.fiuba.tdd.tp.objects.general;

import ar.fiuba.tdd.tp.abilities.control.ChildrenControlFunctions;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.List;

public class ConcreteGameObjectWithChildren extends ConcreteGameObject implements ChildrenControlFunctions, GameObjectWithChildren {

    protected final ChildrenState children;

    public ConcreteGameObjectWithChildren(String name) {
        this(name, new ChildrenState());
    }

    public ConcreteGameObjectWithChildren(String name, ChildrenState children) {
        super(name);
        this.children = children;
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
    public boolean canAddChild(GameObjectWithParent child) {
        return children.canAddChild(child);
    }

    public ChildrenState getChildrenState() {
        return children;
    }

    @Override
    public boolean isEmpty() {
        return children.isEmpty();
    }

    @Override
    public boolean canRemoveChild(String name) {
        return children.canRemoveChild(name);
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
    public List<GameObjectWithParent> getChildren() {
        return children.getChildren();
    }

    @Override
    public boolean containsInHierarchy(String name) {
        return children.containsInHierarchy(name);
    }

    @Override
    public GameObject getChildFromHierarchy(String name) {
        return children.getChildFromHierarchy(name);
    }
}
