package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;

public class LockedDoor extends AbstractLockedOpenable {

    public LockedDoor(String name, GameObjectWithChildren parent, int key) {
        super(name, parent, key);
    }
}
