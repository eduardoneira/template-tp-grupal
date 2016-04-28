package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.BeCursed;
import ar.fiuba.tdd.tp.actions.BeMoved;

/**
 * Created by Master on 28/04/2016.
 */
public class CursedKey extends Key {
    public CursedKey(String name, int key) {
        super(name, key);

        actionsGranted.add(new BeCursed(this));
    }
}
