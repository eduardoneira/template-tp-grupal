package ar.fiuba.tdd.tp.objects.concrete.player;

import ar.fiuba.tdd.tp.actions.*;

public class PlayerCursedObject extends Player {

    public PlayerCursedObject(String name) {
        super(name);
        init();
        addAction(new Talk(this));
        addAction(new HaveEverythingStolen(this, children));
    }

    private void init() {
        addAction(new HaveMovedFrom(this, children));
        addAction(new Pick(this));
        addAction(new Open(this));

    }

}
