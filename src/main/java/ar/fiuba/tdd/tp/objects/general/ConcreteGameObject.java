package ar.fiuba.tdd.tp.objects.general;

import ar.fiuba.tdd.tp.abilities.CanBeLookedAt;

public abstract class ConcreteGameObject implements GameObjectCanBeLookedAt {

    private String name;

    public ConcreteGameObject(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public String lookAt() {
        return getName();
    }
}
