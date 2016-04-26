package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanBeMoved;

public class CanHaveThingsMovedFromStrategy {
    ConcreteGameObjectContainer instance; // ver, tal vez deberia ser una interfaz. asi lo estamos atando a la implementacion

    public CanHaveThingsMovedFromStrategy(ConcreteGameObjectContainer instance){
        this.instance = instance;
    }

    String haveMovedFrom(CanBeMoved objectToMove) {
        instance.removeChild(objectToMove);
        return ""; // VER
    }
}
