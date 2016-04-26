package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanBeClosed;
import ar.fiuba.tdd.tp.actions.CanBeOpened;
import ar.fiuba.tdd.tp.actions.CanCloseThings;
import ar.fiuba.tdd.tp.actions.CanOpen;

public class Door extends ConcreteGameObjectLeaf implements CanBeOpened, CanBeClosed {

    CanBeOpenedStrategy openedStrategy;

    public Door(String name){
        super(name);
        openedStrategy = new CanBeOpenedStrategy();
    }

    public String beOpenedBy(CanOpen opener) {
        return openedStrategy.beOpenedBy(opener);
    }

    public String beClosedBy(CanCloseThings closer) {
        return openedStrategy.beClosedBy(closer);
    }


}
