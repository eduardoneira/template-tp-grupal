package ar.fiuba.tdd.tp.abilities;

import ar.fiuba.tdd.tp.objects.general.GameObjectCanCloseThings;

public interface CanBeClosed {
    String beClosedBy(GameObjectCanCloseThings objectThatDoesTheClosing);
}
