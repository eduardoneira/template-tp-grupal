package ar.fiuba.tdd.tp;
/**
 * Created by Master on 21/04/2016.
 */
public class Inventory extends ConcreteGameObjectContainer {

    public Inventory(String n){
        super(n);
    }

    public void store(TakeableItem o){
        addChild(o);
    }

    public void drop(String n){
        removeChild(n);
    }
}
