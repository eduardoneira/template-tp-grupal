package ar.fiuba.tdd.tp.abilities;


import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public interface CanHaveChildren {

    GameObject getChild(String name);

    boolean contains(String name);

    List<GameObject> getChildren();
}
