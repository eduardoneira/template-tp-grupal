package ar.fiuba.tdd.tp.objects.concrete.player;

import ar.fiuba.tdd.tp.actions.*;

/**
 * Created by Master on 27/04/2016.
 */
public class PlayerCursedObject extends Player {
    public PlayerCursedObject(String name) {
        super(name);

        addAction(new Talk(this));
        addAction(new HaveEverythingStolen(this, children));
        addAction(new HaveMovedFrom(this, children));
        addAction(new Pick(this));
        addAction(new Open(this));
    }
}
