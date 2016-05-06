package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;

public class Treasure extends GeneralMovableObject {
    public Treasure(String name, GameObjectWithChildren parent) {
        super(name, parent);
    }
}
