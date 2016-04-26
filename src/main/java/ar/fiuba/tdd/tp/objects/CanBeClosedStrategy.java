package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanBeClosed;
import ar.fiuba.tdd.tp.actions.CanCloseThings;

public class CanBeClosedStrategy {
    BooleanState open;

    public CanBeClosedStrategy(CanBeClosed instance){
        this.open = new BooleanState(true);
    }

    public CanBeClosedStrategy(BooleanState state){
        open = state;
    }

    // interfaz
    public String beClosedBy(CanCloseThings closer) {
        open.setFalse();
        return "";
    }

    // control
    public void setOpen() {
        open.setTrue();
    }

    public void setClosed() {
        open.setFalse();
    }
}
