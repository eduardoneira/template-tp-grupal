package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanBeMoved;
import ar.fiuba.tdd.tp.actions.CanHaveThingsMovedTo;

/**
 * Created by Master on 26/04/2016.
 */
public class CanHaveThingsMovedToStrategy {
    ConcreteGameObjectContainer instance;

    public CanHaveThingsMovedToStrategy(ConcreteGameObjectContainer instance){
        this.instance = instance;
    }

    String haveMovedTo(CanBeMoved objectToMove) {
        instance.addChild(objectToMove);
        return ""; // VER
    }
}
