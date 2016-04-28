package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.BeLookedAt;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;

public class Cabbage extends ConcreteGameObjectWithParent {

    public Cabbage(String name) {
        super(name);
        addAction(new BeLookedAt(this));
    }
}
