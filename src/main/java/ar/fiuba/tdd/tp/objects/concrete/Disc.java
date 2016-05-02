package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;
import ar.fiuba.tdd.tp.objects.general.GameObject;

public class Disc extends ConcreteGameObjectWithParent {

    int diameter;

    public Disc(String name, GameObject parent, int diameter) {
        super(name, parent);
        this.diameter = diameter;
        //addAction(new BeMovedAddsActionToMover(this, parent, new Move(this)));
        addAction(new BeLookedAt(this));
        addAction(new BeMoved(this, this.parent));
    }

    public int getDiameter() {
        return diameter;
    }

}
