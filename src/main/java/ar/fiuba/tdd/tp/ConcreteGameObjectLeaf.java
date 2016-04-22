package ar.fiuba.tdd.tp;
/**
 * Created by Master on 21/04/2016.
 */
public abstract class ConcreteGameObjectLeaf extends ConcreteGameObject implements  CanBeLookedAt{

    public ConcreteGameObjectLeaf(String n){
        super(n);
    }

    @Override
    public String lookAt() {
        return getName();
    }
}
