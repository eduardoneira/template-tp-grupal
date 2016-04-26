package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.Objects.ConcreteGameObjectContainer;
import ar.fiuba.tdd.tp.Objects.GameObject;

/**
 * Created by Master on 21/04/2016.
 */
public class Room extends ConcreteGameObjectContainer implements CanHaveItemsTakenFrom {

    public Room(String name) {
        super(name);
    }

    @Override
    public TakeableItem takeItem(String itemName) {
        GameObject item = getChild(itemName);
        if (!(item instanceof TakeableItem)) {
            return null; // cant take item
        }
        removeChild(itemName);
        return (TakeableItem) item;
    }

    public void addObject(GameObject obj) {
        addChild(obj);
    }
}
