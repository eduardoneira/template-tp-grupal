package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.abilities.control.LockControlFunctions;
import ar.fiuba.tdd.tp.actions.BeOpenedHasLock;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

public abstract class AbstractLockedOpenable extends AbstractOpenable implements LockControlFunctions {

    protected final BooleanState locked;
    protected final int key;

    public AbstractLockedOpenable(String name, GameObjectWithChildren parent, int key) {
        this(name, parent, key, new BooleanState());
    }

    public AbstractLockedOpenable(String name, GameObjectWithChildren parent, int key, BooleanState locked) {
        super(name, parent);
        this.locked = locked;
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
