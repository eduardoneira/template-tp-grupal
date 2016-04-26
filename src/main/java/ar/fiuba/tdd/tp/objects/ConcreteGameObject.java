package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanBeLookedAt;

public abstract class ConcreteGameObject implements GameObject, CanBeLookedAt {

    private String name;

    public ConcreteGameObject(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public String lookAt(){
        return getName();
    }
}
