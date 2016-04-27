package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.abilities.CanBeClosed;
import ar.fiuba.tdd.tp.abilities.CanBeOpened;
import ar.fiuba.tdd.tp.abilities.control.OpenCloseControlFunctions;
import ar.fiuba.tdd.tp.abilities.control.ParentControlFunctions;
import ar.fiuba.tdd.tp.objects.general.*;
import ar.fiuba.tdd.tp.objects.states.ParentState;
import ar.fiuba.tdd.tp.objects.strategies.openclose.CanBeOpenedClosedStrategy;

public class Door extends ConcreteGameObject implements GameObjectCanBeOpened, GameObjectCanBeClosed,
        OpenCloseControlFunctions, GameObjectCanHaveParent, ParentControlFunctions {

    ParentState parent;
    CanBeOpenedClosedStrategy openedClosedStrategy;

    public Door(String name) {
        super(name);
        openedClosedStrategy = new CanBeOpenedClosedStrategy();
    }

    public Door(String name, CanBeOpenedClosedStrategy openedClosedStrategy) {
        super(name);
        this.openedClosedStrategy = new CanBeOpenedClosedStrategy();
    }

    @Override
    public String beOpenedBy(GameObjectCanOpen opener) {
        return openedClosedStrategy.beOpenedBy(opener);
    }

    @Override
    public String beClosedBy(GameObjectCanCloseThings closer) {
        return openedClosedStrategy.beClosedBy(closer);
    }

    @Override
    public void setOpen() {
        openedClosedStrategy.setOpen();
    }

    @Override
    public void setClosed() {
        openedClosedStrategy.setClosed();
    }

    @Override
    public void setParent(GameObjectCanHaveChildren parent) {
        this.parent.setParent(parent);
    }

    @Override
    public GameObjectCanHaveChildren getParent() {
        return parent.getParent();
    }
}
