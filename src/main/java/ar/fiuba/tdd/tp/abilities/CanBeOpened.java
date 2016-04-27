package ar.fiuba.tdd.tp.abilities;

import ar.fiuba.tdd.tp.objects.general.GameObjectCanOpen;

public interface CanBeOpened {
    String beOpenedBy(GameObjectCanOpen objectThatDoesTheOpening);
}
