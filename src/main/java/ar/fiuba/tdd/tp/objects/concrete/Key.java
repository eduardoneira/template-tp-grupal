package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.BeMoved;
import ar.fiuba.tdd.tp.actions.BeMovedAddsActionToMover;
import ar.fiuba.tdd.tp.actions.Unlock;
import ar.fiuba.tdd.tp.objects.general.*;

public class Key extends ConcreteGameObjectWithParent {

    protected int key;

    public Key(String name, int key) {
        super(name);
        this.key = key;
        addAction(new BeMovedAddsActionToMover(this, parent, new Unlock(this, key)));
    }
}
