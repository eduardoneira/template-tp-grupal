package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.abilities.control.LockControlFunctions;
import ar.fiuba.tdd.tp.actions.open.BeOpenedHasLock;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

public class LockedDoor extends Door implements LockControlFunctions {

    protected BooleanState locked;
    protected int key;

    public LockedDoor(String name, int key) {
        super(name);

        locked = new BooleanState();
        this.key = key;

        addAction(new BeOpenedHasLock(this, open, locked, key));
    }

    @Override
    public void setLocked() {
        locked.setTrue();
    }

    @Override
    public void setUnlocked() {
        locked.setFalse();
    }
}
