package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.abilities.control.LockControlFunctions;
import ar.fiuba.tdd.tp.actions.BeOpenedHasLock;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

public class LockedDoor extends AbstractLockedOpenable {

    public LockedDoor(String name, int key) {
        super(name, key);
    }
}
