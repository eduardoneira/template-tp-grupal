package ar.fiuba.tdd.tp.objects.strategies.openclose;

import ar.fiuba.tdd.tp.abilities.CanBeClosed;
import ar.fiuba.tdd.tp.abilities.CanBeOpened;
import ar.fiuba.tdd.tp.abilities.control.LockControlFunctions;
import ar.fiuba.tdd.tp.abilities.control.OpenCloseControlFunctions;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanCloseThings;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanOpen;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

// asumo que todos los unlockables pueden ser abiertos o cerrados
public class CanBeUnlockedStrategy implements CanBeOpened, CanBeClosed, OpenCloseControlFunctions, LockControlFunctions {
    CanBeOpenedClosedStrategy openedClosedStrategy;
    BooleanState locked;
    int lock;

    public CanBeUnlockedStrategy(int lock) {
        this.lock = lock;
        openedClosedStrategy = new CanBeOpenedClosedStrategy();
        locked = new BooleanState(true);
    }

    public CanBeUnlockedStrategy(int lock, BooleanState locked) {
        this.lock = lock;
        openedClosedStrategy = new CanBeOpenedClosedStrategy();
        this.locked = locked;
    }

    @Override
    public String beOpenedBy(GameObjectCanOpen opener) {
        if (locked.getValue() == false) {
            openedClosedStrategy.beOpenedBy(opener);
        } else {
            // ver si el que abre tiene el lock, entonces abrir normal
        }
        return ""; // VER
    }

    @Override
    public String beClosedBy(GameObjectCanCloseThings closer) {
        openedClosedStrategy.beClosedBy(closer);
        return ""; // VER
    }

    // funciones de control
    @Override
    public void setOpen() {
        openedClosedStrategy.setOpen();
    }

    @Override
    public void setClosed() {
        openedClosedStrategy.setClosed();
    }

    @Override
    public void setLock(int lock) {
        this.lock = lock;
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
