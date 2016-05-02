package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.abilities.control.OpenCloseControlFunctions;
import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.general.*;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

public class Box extends ConcreteGameObjectWithParentAndChildren implements OpenCloseControlFunctions {

    protected BooleanState open;

    public Box(String name, GameObjectWithChildren parent) {
        super(name, parent);
        open = new BooleanState();
        open.setFalse();
        addAction(new HaveMovedFromChangesPermission(this, children, open));
        addAction(new HaveMovedTo(this, children));
        addAction(new BeLookedAtAndChildrenChangeVisibility(this, children, open));
        addAction(new BeOpened(this, open));
        addAction(new BeAskedWhat(this));
    }

    @Override
    public boolean isOpen() {
        return open.getValue();
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
        return open.getValue();
    }
}
