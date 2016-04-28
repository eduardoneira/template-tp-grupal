package ar.fiuba.tdd.tp.objects.concrete.player;

import ar.fiuba.tdd.tp.actions.ChangeRoom;
import ar.fiuba.tdd.tp.actions.HaveMovedFrom;
import ar.fiuba.tdd.tp.actions.Leave;
import ar.fiuba.tdd.tp.actions.Take;

public class PlayerCrossShores extends Player {

    public PlayerCrossShores(String name) {
        super(name);
        addAction(new HaveMovedFrom(this, children));
        addAction(new ChangeRoom(this, parent));
        addAction(new Leave(this));
        addAction(new Take(this));
    }
}
