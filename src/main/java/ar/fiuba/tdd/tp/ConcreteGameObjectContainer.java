package ar.fiuba.tdd.tp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Master on 21/04/2016.
 */
public class ConcreteGameObjectContainer extends ConcreteGameObject implements CanBeLookedAt {
    private Map<String, GameObject> children;

    public ConcreteGameObjectContainer(String n){
        super(n);
        children = new HashMap<String, GameObject>();
    }

    // TODO: ver valores duplicados
    protected void addChild(GameObject o){
        children.put(o.getName(), o);
    }

    protected void removeChild(String name){
        children.remove(name);
    }

    protected GameObject getChild(String name){
        return children.get(name);
    }

    @Override
    public String lookAt() {
        StringBuilder s = new StringBuilder();
        s.append(getName());
        s.append(" with ");
        for(String n : children.keySet()){
            s.append(getChild(n).lookAt());
            s.append(" and ");
        }
        return s.toString();
    }
}
