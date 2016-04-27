package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.abilities.control.LockControlFunctions;
import ar.fiuba.tdd.tp.actions.open.BeOpenedHasLock;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.Random;

/**
 * Created by Master on 27/04/2016.
 */
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
