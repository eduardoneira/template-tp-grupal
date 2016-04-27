package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.abilities.CanBeClosed;
import ar.fiuba.tdd.tp.abilities.CanBeOpened;
import ar.fiuba.tdd.tp.abilities.control.OpenCloseControlFunctions;
import ar.fiuba.tdd.tp.abilities.control.ParentControlFunctions;
import ar.fiuba.tdd.tp.newactions.open.BeOpened;
import ar.fiuba.tdd.tp.objects.general.*;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import ar.fiuba.tdd.tp.objects.states.ParentState;
import ar.fiuba.tdd.tp.objects.strategies.openclose.CanBeOpenedClosedStrategy;

public class Door extends ConcreteGameObjectWithParent implements OpenCloseControlFunctions {

    protected BooleanState open;

    public Door(String name) {
        super(name);
        open = new BooleanState();

        addAction(new BeOpened(this, open));
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
        return open.getValue();
    }

    @Override
    public boolean isClosed() {
        return !open.getValue();
    }
}
