package ar.fiuba.tdd.tp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Master on 21/04/2016.
 */
public class ConcreteGameObjectContainer extends ConcreteGameObject {
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

    protected GameObject getChild(String name) {
        return children.get(name);
    }
}
