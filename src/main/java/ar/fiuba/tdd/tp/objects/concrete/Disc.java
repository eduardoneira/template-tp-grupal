package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.BeMovedAddsActionToMover;
import ar.fiuba.tdd.tp.actions.Move;
import ar.fiuba.tdd.tp.actions.Unlock;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;

/**
 * Created by fernando on 28/04/16.
 */
public class Disc extends ConcreteGameObjectWithParent {
    int diameter;
    String name;
    public Disc (String name,int diameter){
        super(name);
        this.diameter = diameter;
        //addAction(new BeMovedAddsActionToMover(this, parent, new Move(this)));

    }
    public int getDiameter() {
        return diameter;
    }

}
