package ar.fiuba.tdd.tp.abilities;


import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveParent;

public interface CanHaveChildren {

    GameObjectCanHaveParent getChild(String name);

    boolean contains(String name);
}
