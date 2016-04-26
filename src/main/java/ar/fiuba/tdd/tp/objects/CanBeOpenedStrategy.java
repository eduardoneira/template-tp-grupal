package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanBeOpened;
import ar.fiuba.tdd.tp.actions.CanCloseThings;
import ar.fiuba.tdd.tp.actions.CanOpen;

public class CanBeOpenedStrategy implements OpenStrategy {
    CanBeOpened instance;
    boolean open;

    public CanBeOpenedStrategy(CanBeOpened instance){
        this.instance = instance;
    }

    public String beOpenedBy(CanOpen opener) {
        // tal vez diferenciar si ya estaba abierto?
        open = true;
        return ""; // VER
    }
}
