package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.Objects.ConcreteGameObjectLeaf;

/**
 * Created by Master on 21/04/2016.
 */
public class Stick extends ConcreteGameObjectLeaf implements TakeableItem {

    public Stick(String name) {
        super(name);
    }
}
