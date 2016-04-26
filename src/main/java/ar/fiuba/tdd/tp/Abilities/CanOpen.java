package ar.fiuba.tdd.tp.abilities;

import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeOpened;

public interface CanOpen {
    String open(GameObjectCanBeOpened objectThatIsOpened);
}
