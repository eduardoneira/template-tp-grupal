package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.abilities.control.OpenCloseControlFunctions;
import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

/**
 * Created by Master on 03/05/2016.
 */
public class Container extends ConcreteGameObjectWithParentAndChildren implements OpenCloseControlFunctions {

    protected BooleanState open;

    public Container(String name, GameObjectWithChildren parent) {
        this(name, parent, new ChildrenState());
    }

    public Container(String name, GameObjectWithChildren parent, ChildrenState children) {
        super(name, parent, children);
        open = new BooleanState();
        open.setFalse();
        addAction(new HaveMovedFromChangesPermission(this, this.children, open));
        addAction(new HaveMovedTo(this, children));
        addAction(new BeLookedAtAndChildrenChangeVisibility(this, this.children, open));
        addAction(new BeOpened(this, open));
        addAction(new BeAskedWhat(this));
    }

    @Override
    public boolean isOpen() {
        return open.isTrue();
    }

    @Override
    public void setOpen() {
        open.setTrue();
    }

    @Override
    public void setClosed() {
        open.setFalse();
    }

    @Override
    public boolean isClosed() {
        return open.isTrue();
    }
}