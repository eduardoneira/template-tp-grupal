package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanOpen;

public interface OpenStrategy {
    String beOpenedBy(CanOpen opener);
}
