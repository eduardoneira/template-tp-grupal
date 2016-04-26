package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanCloseThings;
import ar.fiuba.tdd.tp.actions.CanOpen;

public class CanBeOpenedStrategy {

    String beOpenedBy(CanOpen opener) {
        return "opened!";
    }

    String beClosedBy(CanCloseThings closer) {
        return "closed!";
    }
}
