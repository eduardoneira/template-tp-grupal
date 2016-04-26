package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.*;

/**
 * Created by Master on 26/04/2016.
 */
public class CanBeOpenedClosedStrategy {
    CanBeOpenedClosed instance;
    boolean open;

    public CanBeOpenedClosedStrategy(CanBeOpenedClosed instance){
        this.instance = instance;
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
