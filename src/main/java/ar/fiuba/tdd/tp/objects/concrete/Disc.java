package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;

public class Disc extends ConcreteGameObjectWithParent {

    final int diameter;

    public Disc(String name, GameObjectWithChildren parent, int diameter) {
        super(name, parent);
        this.diameter = diameter;
        //addAction(new BeMovedAddsActionToMover(this, parent, new Move(this)));
        addAction(new BeLookedAt(this));
        addAction(new BeMoved(this, this.parent));
        addAction(new BeAskedWhat(this));
    }

    public int getDiameter() {
        return diameter;
    }

}
