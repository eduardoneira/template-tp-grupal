package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.lookat.BeLookedAt;
import ar.fiuba.tdd.tp.actions.move.BeMoved;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;

public class Wolf extends ConcreteGameObjectWithParent {

    public Wolf(String name) {
        super(name);
        addAction(new BeLookedAt(this));
        addAction(new BeMoved(this, parent));
    }
}
