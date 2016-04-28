package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.abilities.control.LockControlFunctions;
import ar.fiuba.tdd.tp.actions.BeOpenedHasLock;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

/**
 * Created by Master on 28/04/2016.
 */
public abstract class AbstractLockedOpenable extends AbstractOpenable implements LockControlFunctions {

    protected BooleanState locked;
    protected int key;

    public AbstractLockedOpenable(String name, int key) {
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
