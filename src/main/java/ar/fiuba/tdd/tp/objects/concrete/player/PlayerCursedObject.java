package ar.fiuba.tdd.tp.objects.concrete.player;

import ar.fiuba.tdd.tp.actions.move.HaveEverythingStolen;
import ar.fiuba.tdd.tp.actions.talk.Talk;

/**
 * Created by Master on 27/04/2016.
 */
public class PlayerCursedObject extends Player {
    public PlayerCursedObject(String name) {
        super(name);

        addAction(new Talk(this));
        addAction(new HaveEverythingStolen(this, children));
    }
}
