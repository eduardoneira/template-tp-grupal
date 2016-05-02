package ar.fiuba.tdd.tp.objects.concrete.player;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;

public class PlayerCursedObject extends Player {

    public PlayerCursedObject(String name, GameObjectWithChildren parent) {
        super(name, parent);
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
