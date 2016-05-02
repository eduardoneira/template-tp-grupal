package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.abilities.control.OpenCloseControlFunctions;
import ar.fiuba.tdd.tp.actions.BeOpened;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObject;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

public abstract class AbstractOpenable extends ConcreteGameObjectWithParent implements OpenCloseControlFunctions{

    protected BooleanState open;

    public AbstractOpenable(String name, GameObject parent) {
        super(name, parent);
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
