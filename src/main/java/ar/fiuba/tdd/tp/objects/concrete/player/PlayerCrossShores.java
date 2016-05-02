package ar.fiuba.tdd.tp.objects.concrete.player;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.general.GameObject;

public class PlayerCrossShores extends Player {

    public PlayerCrossShores(String name, GameObject parent) {
        super(name, parent);
        init();
    }

    private void init() {
        addAction(new Cross(this, parent));
        addAction(new Leave(this));
        addAction(new Take(this));
        addAction(new HaveMovedFrom(this, children));
    }
}
