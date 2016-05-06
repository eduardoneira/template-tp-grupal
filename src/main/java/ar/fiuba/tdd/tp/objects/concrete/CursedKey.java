package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.BeCursed;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;

public class CursedKey extends Key {
    public CursedKey(String name, GameObjectWithChildren parent, int key) {
        super(name, parent, key);

        actionsGranted.add(new BeCursed(this));
    }
}
