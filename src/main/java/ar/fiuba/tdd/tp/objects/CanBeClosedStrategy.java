package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanBeClosed;
import ar.fiuba.tdd.tp.actions.CanCloseThings;

public class CanBeClosedStrategy {
    CanBeClosed instance;
    boolean closed;

    public CanBeClosedStrategy(CanBeClosed instance){
        this.instance = instance;
    }

    public String beClosedBy(CanCloseThings closer) {
        closed = true;
        return "";
    }
}
