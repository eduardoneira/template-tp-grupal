package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanCloseThings;
import ar.fiuba.tdd.tp.actions.CanOpen;

/**
 * Created by Master on 26/04/2016.
 */
// asumo que todos los unlockables pueden ser abiertos o cerrados
public class CanBeUnlockedStrategy {
    CanBeOpenedClosedStrategy openedClosedStrategy;
    BooleanState locked;
    int lock;

    public CanBeUnlockedStrategy(int lock){
        this.lock = lock;
        openedClosedStrategy = new CanBeOpenedClosedStrategy();
        locked = new BooleanState(true);
    }

    public CanBeUnlockedStrategy(int lock, BooleanState locked){
        this.lock = lock;
        openedClosedStrategy = new CanBeOpenedClosedStrategy();
        this.locked = locked;
    }

    public String beOpenedBy(CanOpen opener) {
        if(locked.getValue() == false){
            openedClosedStrategy.beOpenedBy(opener);
        } else {
            // ver si el que abre tiene el lock, entonces abrir normal
        }
        return ""; // VER
    }

    public String beClosedBy(CanCloseThings closer) {
        openedClosedStrategy.beClosedBy(closer);
        return ""; // VER
    }

    // funciones de control para ser usadas por clases que extiendan esta funcionalidad
    public void setOpen(){
        openedClosedStrategy.setOpen();
    }

    public void setClosed(){
        openedClosedStrategy.setClosed();
    }

    public void setLock(int lock) {
        this.lock = lock;
    }

    public void setLocked() {
        locked.setTrue();
    }

    public void setUnlocked() {
        locked.setFalse();
    }
}
