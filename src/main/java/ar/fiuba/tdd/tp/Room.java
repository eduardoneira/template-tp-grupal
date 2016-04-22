package ar.fiuba.tdd.tp;
/**
 * Created by Master on 21/04/2016.
 */
public class Room extends ConcreteGameObjectContainer implements CanHaveItemsTakenFrom {

    public Room(String n){
        super(n);
    }

    @Override
    public TakeableItem takeItem(String n) {
        GameObject o = getChild(n);
        if(!(o instanceof TakeableItem)){
            return null; // cant take item
        }
        removeChild(n);
        return (TakeableItem) o;
    }

    public void addItem(GameObject o){
        addChild(o);
    }
}
