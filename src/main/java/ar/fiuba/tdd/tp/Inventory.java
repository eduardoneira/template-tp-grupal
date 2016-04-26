package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.Objects.ConcreteGameObjectContainer;

/**
 * Created by Master on 21/04/2016.
 */
public class Inventory extends ConcreteGameObjectContainer {

    public Inventory(String name) {
        super(name);
    }

    public void store(TakeableItem obj) {
        addChild(obj);
    }

    public void drop(String itemName) {
        removeChild(itemName);
    }
}
