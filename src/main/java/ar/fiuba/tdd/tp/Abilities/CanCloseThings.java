package ar.fiuba.tdd.tp.abilities;

import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeClosed;

public interface CanCloseThings {
    String close(GameObjectCanBeClosed objectToClosed);
}
