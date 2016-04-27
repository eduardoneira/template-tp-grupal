package ar.fiuba.tdd.tp.objects.strategies.openclose;

import ar.fiuba.tdd.tp.abilities.CanBeOpened;
import ar.fiuba.tdd.tp.abilities.control.OpenCloseControlFunctions;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanOpen;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

public class CanBeOpenedStrategy implements CanBeOpened, OpenCloseControlFunctions {
    CanBeOpened instance;
    BooleanState open;

    public CanBeOpenedStrategy() {
        open = new BooleanState(false);
    }

    public CanBeOpenedStrategy(BooleanState state) {
        open = state;
    }

    @Override
    public String beOpenedBy(GameObjectCanOpen opener) {
        // tal vez diferenciar si ya estaba abierto?
        open.setTrue();
        return ""; // VER
    }

    // funciones de control para ser usadas por clases que extiendan esta funcionalidad
    @Override
    public void setOpen() {
        open.setTrue();
    }

    @Override
    public void setClosed() {
        open.setFalse();
    }
}
