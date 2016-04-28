package ar.fiuba.tdd.tp.objects.concrete.player;

import ar.fiuba.tdd.tp.actions.*;

public class PlayerCrossShores extends Player {

    public PlayerCrossShores(String name) {
        super(name);
        addAction(new HaveMovedFrom(this, children));
        addAction(new Cross(this, parent));
        addAction(new Leave(this));
        addAction(new Take(this));
    }
}
