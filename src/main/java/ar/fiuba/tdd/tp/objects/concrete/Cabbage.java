package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.BeLookedAt;
import ar.fiuba.tdd.tp.actions.BeMoved;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;

public class Cabbage extends ConcreteGameObjectWithParent {

    public Cabbage(String name, GameObjectWithChildren parent) {
        super(name, parent);
        addAction(new BeLookedAt(this));
        addAction(new BeMoved(this, this.parent));
    }
}
