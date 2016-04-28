package ar.fiuba.tdd.tp.objects.concrete;


import ar.fiuba.tdd.tp.actions.BeLookedAt;
import ar.fiuba.tdd.tp.actions.BeMoved;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;

public class Sheep extends ConcreteGameObjectWithParent {

    public Sheep(String name) {
        super(name);
        addAction(new BeLookedAt(this));
        addAction(new BeMoved(this, parent));
    }
}
