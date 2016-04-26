package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.CanBeMoved;
import ar.fiuba.tdd.tp.actions.CanHaveThingsMovedTo;
import ar.fiuba.tdd.tp.objects.ConcreteGameObjectContainer;
import ar.fiuba.tdd.tp.objects.GameObject;

public class Room extends ConcreteGameObjectContainer implements CanHaveThingsMovedTo {

    public Room(String name) {
        super(name);
    }

    public CanBeMoved takeItem(String itemName) {
        GameObject item = getChild(itemName);
        if (!(item instanceof CanBeMoved)) {
            return null; // cant take item
        }
        removeChild(itemName);
        return (CanBeMoved) item;
    }

    public String haveMovedTo(CanBeMoved obj) {
        addChild(obj);
        return "There you go!";
    }
}
