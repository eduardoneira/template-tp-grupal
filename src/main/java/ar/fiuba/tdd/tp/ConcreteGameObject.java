package ar.fiuba.tdd.tp;
/**
 * Created by Master on 21/04/2016.
 */
public abstract class ConcreteGameObject implements GameObject {
    private String name;

    public ConcreteGameObject(String n){
        this.name = n;
    }

    public String getName() { return name; }
}
