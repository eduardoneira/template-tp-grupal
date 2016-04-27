package ar.fiuba.tdd.tp.objects.states;

import ar.fiuba.tdd.tp.abilities.control.ChildrenControlFunctions;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveParent;

import java.util.*;

/**
 * Created by Master on 26/04/2016.
 */
public class ChildrenState implements ChildrenControlFunctions {
    private Map<String, GameObjectCanHaveParent> children;

    public ChildrenState() {
        children = new HashMap<String, GameObjectCanHaveParent>();
    }

    @Override
    public void addChild(GameObjectCanHaveParent object) {
        children.put(object.getName(), object);
    }

    @Override
    public void removeChild(GameObjectCanHaveParent object) {
        children.remove(object.getName());
    }

    @Override
    public GameObjectCanHaveParent getChild(String name) {
        return children.get(name);
    }

    @Override
    public boolean contains(String name) {
        return children.containsKey(name);
    }
}
