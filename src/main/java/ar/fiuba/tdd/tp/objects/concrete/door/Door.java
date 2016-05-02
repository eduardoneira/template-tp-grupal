package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;

public class Door extends AbstractOpenable {

    public Door(String name, GameObjectWithChildren parent) {
        super(name, parent);
    }
}
