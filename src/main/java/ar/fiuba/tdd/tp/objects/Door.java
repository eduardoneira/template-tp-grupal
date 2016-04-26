package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.*;

public class Door extends ConcreteGameObjectLeaf implements CanBeOpened, CanBeClosed {

    CanBeOpenedClosedStrategy openedClosedStrategy;

    public Door(String name) {
        super(name);
        openedClosedStrategy = new CanBeOpenedClosedStrategy();
    }

    public String beOpenedBy(CanOpen opener) {
        return openedClosedStrategy.beOpenedBy(opener);
    }

    public String beClosedBy(CanCloseThings closer) {
        return openedClosedStrategy.beClosedBy(closer);
    }


}
