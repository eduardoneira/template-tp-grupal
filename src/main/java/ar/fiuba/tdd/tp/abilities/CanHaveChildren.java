package ar.fiuba.tdd.tp.abilities;


import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveParent;

public interface CanHaveChildren {

    GameObject getChild(String name);

    boolean contains(String name);
}
