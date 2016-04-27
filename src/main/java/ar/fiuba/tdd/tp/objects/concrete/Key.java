package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.move.BeMoved;
import ar.fiuba.tdd.tp.objects.general.*;

public class Key extends ConcreteGameObjectWithParent {

    public Key(String name) {
        super(name);

        addAction(new BeMoved(this, parent));
    }
}
