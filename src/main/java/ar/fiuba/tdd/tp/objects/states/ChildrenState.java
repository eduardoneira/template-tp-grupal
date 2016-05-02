package ar.fiuba.tdd.tp.objects.states;

import ar.fiuba.tdd.tp.abilities.control.ChildrenControlFunctions;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithParent;

import java.util.*;

public class ChildrenState implements ChildrenControlFunctions {

    private Map<String, GameObjectWithParent> children;

    public ChildrenState() {
        children = new HashMap<>();
    }

    @Override
    public void addChild(GameObjectWithParent object) {
        children.put(object.getName(), object);
    }

    @Override
    public GameObjectWithParent getChild(String name) {
        return children.get(name);
    }

    @Override
    public void removeChild(GameObjectWithParent object) {
        children.remove(object.getName());
    }

    @Override
    public boolean isEmpty() {
        return children.isEmpty();
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
