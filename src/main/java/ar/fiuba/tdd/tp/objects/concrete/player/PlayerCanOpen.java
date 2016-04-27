package ar.fiuba.tdd.tp.objects.concrete.player;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeOpened;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanOpen;

/**
 * Created by Master on 27/04/2016.
 */
public class PlayerCanOpen extends Player implements GameObjectCanOpen {

    public PlayerCanOpen(String name) {
        super(name);
    }

    @Override
    public String open(GameObjectCanBeOpened objectThatIsOpened) {
        return objectThatIsOpened.beOpenedBy(this);
    }
}
