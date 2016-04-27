package ar.fiuba.tdd.tp.objects.strategies.openclose;

import ar.fiuba.tdd.tp.abilities.CanBeClosed;
import ar.fiuba.tdd.tp.abilities.CanBeOpened;
import ar.fiuba.tdd.tp.abilities.control.OpenCloseControlFunctions;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanCloseThings;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanOpen;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

public class CanBeOpenedClosedStrategy implements CanBeOpened, CanBeClosed, OpenCloseControlFunctions {
    CanBeOpenedStrategy openedStrategy;
    CanBeClosedStrategy closedStrategy;

    public CanBeOpenedClosedStrategy() {
        BooleanState state = new BooleanState(false);
        openedStrategy = new CanBeOpenedStrategy(state);
        closedStrategy = new CanBeClosedStrategy(state);
    }

    public CanBeOpenedClosedStrategy(BooleanState state) {
        openedStrategy = new CanBeOpenedStrategy(state);
        closedStrategy = new CanBeClosedStrategy(state);
    }

    @Override
    public String beOpenedBy(GameObjectCanOpen opener) {
        // tal vez diferenciar si ya estaba abierto?
        openedStrategy.beOpenedBy(opener);
        return ""; // VER
    }

    @Override
    public String beClosedBy(GameObjectCanCloseThings closer) {
        closedStrategy.beClosedBy(closer);
        return ""; // VER
    }

    // funciones de control para ser usadas por clases que extiendan esta funcionalidad
    @Override
    public void setOpen() {
        openedStrategy.setOpen();
        closedStrategy.setOpen(); // redundante
    }

    @Override
    public void setClosed() {
        openedStrategy.setClosed(); // redundante
        closedStrategy.setClosed();
    }
}
