package ar.fiuba.tdd.tp;
/**
 * Created by Master on 21/04/2016.
 */
public abstract class ConcreteGameObject implements GameObject {
    private String name;

    public ConcreteGameObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
