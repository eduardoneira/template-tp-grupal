package ar.fiuba.tdd.tp.objects.states;

import ar.fiuba.tdd.tp.abilities.control.ChildrenControlFunctions;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.*;

/**
 * Created by Master on 26/04/2016.
 */
public class ChildrenState implements ChildrenControlFunctions {
    private Map<String, GameObject> children;

    public ChildrenState() {
        children = new HashMap<String, GameObject>();
    }

    @Override
    public void addChild(GameObject object) {
        children.put(object.getName(), object);
    }

    @Override
    public void removeChild(GameObject object) {
        children.remove(object.getName());
    }

    @Override
    public GameObject getChild(String name) {
        return children.get(name);
    }

    @Override
    public boolean contains(String name) {
        return children.containsKey(name);
    }

    @Override
    public List<GameObject> getChildren() {
        return new ArrayList<GameObject>(children.values());
    }
}
