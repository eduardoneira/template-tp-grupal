package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.objects.general.GameObject;

public class LockedDoor extends AbstractLockedOpenable {

    public LockedDoor(String name, GameObject parent, int key) {
        super(name, parent, key);
    }
}
