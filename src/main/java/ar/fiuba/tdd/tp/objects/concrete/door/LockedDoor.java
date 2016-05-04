package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

public class LockedDoor extends AbstractLockedOpenable {

    public LockedDoor(String name, GameObjectWithChildren parent, int key) {
        super(name, parent, key);
    }
}
