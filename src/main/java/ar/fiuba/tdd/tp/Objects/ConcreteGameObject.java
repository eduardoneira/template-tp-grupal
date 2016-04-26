package ar.fiuba.tdd.tp.Objects;

import ar.fiuba.tdd.tp.Actions.CanBeLookedAt;
import ar.fiuba.tdd.tp.Objects.GameObject;

/**
 * Created by Master on 21/04/2016.
 */
public abstract class ConcreteGameObject implements GameObject, CanBeLookedAt {
    private String name;

    public ConcreteGameObject(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
