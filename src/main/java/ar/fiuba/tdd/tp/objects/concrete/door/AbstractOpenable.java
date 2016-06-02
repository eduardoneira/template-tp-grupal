package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.abilities.control.OpenCloseControlFunctions;
import ar.fiuba.tdd.tp.actions.BeAskedWhat;
import ar.fiuba.tdd.tp.actions.BeLookedAt;
import ar.fiuba.tdd.tp.actions.BeOpened;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

public abstract class AbstractOpenable extends ConcreteGameObjectWithParent implements OpenCloseControlFunctions {

    protected final BooleanState open;

    public AbstractOpenable(String name, GameObjectWithChildren parent) {
        super(name, parent);
        open = new BooleanState();

        addAction(new BeOpened(this, open));
        addAction(new BeAskedWhat(this));
        addAction(new BeLookedAt(this));
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
    public boolean isOpen() {
        return open.isTrue();
    }

    @Override
    public boolean isClosed() {
        return !open.isTrue();
    }

    public BooleanState getOpenState() {
        return open;
    }
}
