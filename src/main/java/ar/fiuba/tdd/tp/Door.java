package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.abilities.CanBeClosed;
import ar.fiuba.tdd.tp.abilities.CanBeOpened;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanCloseThings;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanOpen;
import ar.fiuba.tdd.tp.objects.strategies.openclose.CanBeOpenedClosedStrategy;

public class Door extends ConcreteGameObject implements CanBeOpened, CanBeClosed {

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
}
