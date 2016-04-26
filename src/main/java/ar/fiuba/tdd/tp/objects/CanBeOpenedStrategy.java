package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanCloseThings;
import ar.fiuba.tdd.tp.actions.CanOpen;

public class CanBeOpenedStrategy implements OpenStrategy {

    public String beOpenedBy(CanOpen opener) {
        return "opened!";
    }

    public String beClosedBy(CanCloseThings closer) {
        return "closed!";
    }
}
