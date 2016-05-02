package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.abilities.control.LockControlFunctions;
import ar.fiuba.tdd.tp.actions.BeOpenedHasLock;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

public abstract class AbstractLockedOpenable extends AbstractOpenable implements LockControlFunctions {

    protected BooleanState locked;
    protected int key;

    public AbstractLockedOpenable(String name, GameObject parent, int key) {
        super(name, parent);
        locked = new BooleanState();
        this.key = key;

        addAction(new BeOpenedHasLock(this, open, locked, this.key));
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
