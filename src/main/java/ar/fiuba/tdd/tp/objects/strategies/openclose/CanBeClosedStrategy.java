package ar.fiuba.tdd.tp.objects.strategies.openclose;

import ar.fiuba.tdd.tp.abilities.CanBeClosed;
import ar.fiuba.tdd.tp.abilities.control.OpenCloseControlFunctions;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanCloseThings;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

public class CanBeClosedStrategy implements CanBeClosed, OpenCloseControlFunctions {
    BooleanState open;

    public CanBeClosedStrategy(CanBeClosed instance) {
        this.open = new BooleanState(true);
    }

    public CanBeClosedStrategy(BooleanState state) {
        open = state;
    }

    @Override
    public String beClosedBy(GameObjectCanCloseThings closer) {
        open.setFalse();
        return "";
    }

    // control
    @Override
    public void setOpen() {
        open.setTrue();
    }

    @Override
    public void setClosed() {
        open.setFalse();
    }
}
