package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.abilities.control.OpenCloseControlFunctions;
import ar.fiuba.tdd.tp.abilities.control.ParentControlFunctions;
import ar.fiuba.tdd.tp.newactions.lookat.BeLookedAtAndChildrenChangeVisibility;
import ar.fiuba.tdd.tp.newactions.move.HaveMovedFrom;
import ar.fiuba.tdd.tp.newactions.move.HaveMovedTo;
import ar.fiuba.tdd.tp.objects.general.*;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import ar.fiuba.tdd.tp.objects.states.ParentState;

public class Box extends ConcreteGameObjectWithParentAndChildren implements OpenCloseControlFunctions {

    protected BooleanState open;

    public Box(String name) {
        super(name);

        open = new BooleanState();
        open.setFalse();
        addAction(new HaveMovedFrom(this, children));
        addAction(new HaveMovedTo(this, children));
        addAction(new BeLookedAtAndChildrenChangeVisibility(this, children, open));
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
        return open.getValue();
    }
}
