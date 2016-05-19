package ar.fiuba.tdd.tp.objects.general;

import ar.fiuba.tdd.tp.objects.states.ParentState;

public class ConcreteGameObjectWithParent extends ConcreteGameObject implements GameObjectWithParent {

    protected final ParentState parent;

    public ConcreteGameObjectWithParent(String name, GameObjectWithChildren parent) {
        this(name, parent, new ParentState());
    }

    @Override
    public boolean canBeChangedTo(GameObjectWithChildren parent) {
        return this.parent.canBeChangedTo(parent);
    }

    public ConcreteGameObjectWithParent(String name, GameObjectWithChildren parent, ParentState parentState) {
        super(name);
        this.parent = parentState;
        setParent(parent);
        parent.addChild(this);
    }

    @Override
    public void setParent(GameObjectWithChildren parent) {
        this.parent.setParent(parent);
    }

    @Override
    public GameObject getParent() {
        return this.parent.getParent();
    }

    @Override
    public boolean containsInHierarchy(String name) {
        return false;
    }

    @Override
    public GameObject getChildFromHierarchy(String name) {
        return null;
    }

    public ParentState getParentState() {
        return parent;
    }
}
