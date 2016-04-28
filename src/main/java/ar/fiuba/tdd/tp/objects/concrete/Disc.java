package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;

public class Disc extends ConcreteGameObjectWithParent {
    int diameter;
    public Disc (String name,int diameter){
        super(name);
        this.diameter = diameter;
        //addAction(new BeMovedAddsActionToMover(this, parent, new Move(this)));
        addAction(new BeLookedAt(this));
        addAction(new BeMoved(this, parent));

    }
    public int getDiameter() {
        return diameter;
    }

}
