package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.*;

import java.util.HashMap;
import java.util.Map;

public class ConcreteGameObjectContainer extends ConcreteGameObject implements CanHavePlaced, CanHaveThingsMovedTo, CanHaveThingsMovedFrom {
    private Map<String, GameObject> children;

    public ConcreteGameObjectContainer(String name) {
        super(name);
        children = new HashMap<String, GameObject>();
    }

    // TODO: ver valores duplicados
    protected void addChild(GameObject obj) {
        children.put(obj.getName(), obj);
    }

    protected void removeChild(String name) {
        children.remove(name);
    }

    protected void removeChild(GameObject object) {
        children.remove(object.getName());
    }

    public boolean contains(String name) {
        return children.containsKey(name);
    }

    public GameObject getChild(String name) {
        return children.get(name);
    }

    public void place(CanBePlaced toPlace){
        this.addChild((GameObject) toPlace);
    }

    public String haveMovedTo(CanBeMoved toMove) {
        addChild(toMove);
        toMove.setParent(this);
        return "";
    }

    public String haveMovedFrom(CanBeMoved toMove) {
        removeChild(toMove);
        return "";
    }
}
