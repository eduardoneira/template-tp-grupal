package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.BeCursed;
import ar.fiuba.tdd.tp.actions.BeMoved;
import ar.fiuba.tdd.tp.objects.general.GameObject;

public class CursedKey extends Key {
    public CursedKey(String name, GameObject parent, int key) {
        super(name, parent, key);

        actionsGranted.add(new BeCursed(this));
    }
}
