package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.*;

public class CanBeOpenedClosedStrategy implements OpenStrategy {
    CanBeOpenedClosed instance;
    boolean open;

    public CanBeOpenedClosedStrategy(){
    }

    public String beOpenedBy(CanOpen opener) {
        // tal vez diferenciar si ya estaba abierto?
        open = true;
        return ""; // VER
    }

    public String beClosedBy(CanCloseThings closer) {
        open = false;
        return ""; // VER
    }
}
