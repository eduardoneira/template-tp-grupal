package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.objects.ConcreteGameObjectContainer;
import ar.fiuba.tdd.tp.objects.GameObject;

public class Inventory extends ConcreteGameObjectContainer {

    public Inventory(String name) {
        super(name);
    }

    public void store(GameObject obj) {
        addChild(obj);
    }

    public void drop(String itemName) {
        removeChild(itemName);
    }

}
