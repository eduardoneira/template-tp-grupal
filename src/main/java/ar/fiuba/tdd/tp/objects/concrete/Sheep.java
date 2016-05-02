package ar.fiuba.tdd.tp.objects.concrete;


import ar.fiuba.tdd.tp.actions.BeLookedAt;
import ar.fiuba.tdd.tp.actions.BeMoved;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;
import ar.fiuba.tdd.tp.objects.general.GameObject;

public class Sheep extends ConcreteGameObjectWithParent {

    public Sheep(String name, GameObject parent) {
        super(name, parent);
        addAction(new BeLookedAt(this));
        addAction(new BeMoved(this, this.parent));
    }
}
