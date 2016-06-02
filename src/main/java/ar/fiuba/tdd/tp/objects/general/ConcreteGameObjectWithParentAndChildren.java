package ar.fiuba.tdd.tp.objects.general;

import ar.fiuba.tdd.tp.objects.states.ChildrenState;
import ar.fiuba.tdd.tp.objects.states.ParentState;

import java.util.List;

public class ConcreteGameObjectWithParentAndChildren extends ConcreteGameObject
        implements GameObjectWithChildren, GameObjectWithParent {

    protected final ChildrenState children;
    protected final ParentState parent;

    public ConcreteGameObjectWithParentAndChildren(String name, GameObjectWithChildren parent) {
        this(name, parent, new ChildrenState());
    }

    public ConcreteGameObjectWithParentAndChildren(String name, GameObjectWithChildren parent, ChildrenState children) {
        super(name);
        this.children = children;
        this.parent = new ParentState();
        if (parent != null) {
            setParent(parent);
            parent.addChild(this);
        }
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

    public ParentState getParentState() {
        return parent;
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
    public boolean canAddChild(GameObjectWithParent child) {
        return children.canAddChild(child);
    }

    @Override
    public boolean canRemoveChild(String name) {
        return children.canRemoveChild(name);
    }

    @Override
    public void setParent(GameObjectWithChildren parent) {
        this.parent.setParent(parent);
    }

    @Override
    public boolean canBeChangedTo(GameObjectWithChildren parent) {
        return this.parent.canBeChangedTo(parent);
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
