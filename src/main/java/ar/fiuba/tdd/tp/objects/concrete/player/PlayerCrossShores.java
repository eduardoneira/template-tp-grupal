package ar.fiuba.tdd.tp.objects.concrete.player;

import ar.fiuba.tdd.tp.actions.HaveMovedFrom;
import ar.fiuba.tdd.tp.actions.Cross;
import ar.fiuba.tdd.tp.actions.Leave;
import ar.fiuba.tdd.tp.actions.Take;

public class PlayerCrossShores extends Player {

    public PlayerCrossShores(String name) {
        super(name);
        addAction(new HaveMovedFrom(this, children));
        addAction(new Cross(this, parent));
        addAction(new Leave(this));
        addAction(new Take(this));
    }
}
