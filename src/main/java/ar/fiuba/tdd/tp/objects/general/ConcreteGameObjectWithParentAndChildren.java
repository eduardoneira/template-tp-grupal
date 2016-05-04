package ar.fiuba.tdd.tp.objects.general;

import ar.fiuba.tdd.tp.objects.states.ChildrenState;
import ar.fiuba.tdd.tp.objects.states.ParentState;

import java.util.List;

public class ConcreteGameObjectWithParentAndChildren extends ConcreteGameObject
        implements GameObjectWithChildren, GameObjectWithParent {

    protected ChildrenState children;
    protected ParentState parent;

    public ConcreteGameObjectWithParentAndChildren(String name, GameObjectWithChildren parent) {
        this(name, parent, new ChildrenState());
    }

    public ConcreteGameObjectWithParentAndChildren(String name, GameObjectWithChildren parent, ChildrenState children) {
        super(name);
        this.children = children;
        this.parent = new ParentState();
        setParent(parent);
    }

    @Override
    public void addChild(GameObjectWithParent child) {
        children.addChild(child);
    }

    @Override
    public boolean contains(String name) {
        return children.contains(name);
    }

    @Override
    public void removeChild(GameObjectWithParent child) {
        children.removeChild(child);
    }

    @Override
    public GameObjectWithParent getChild(String name) {
        return children.getChild(name);
    }

    public ChildrenState getChildrenState() {
        return children;
    }

    @Override
    public List<GameObject> getChildren() {
        return children.getChildren();
    }

    @Override
    public boolean isEmpty() {
        return children.isEmpty();
    }

    @Override
    public boolean canAddChild() {
        return children.canAddChild();
    }

    @Override
    public void setParent(GameObjectWithChildren parent) {
        this.parent.setParent(parent);
    }

    @Override
    public GameObjectWithChildren getParent() {
        return this.parent.getParent();
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
