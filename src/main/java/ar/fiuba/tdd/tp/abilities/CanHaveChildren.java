package ar.fiuba.tdd.tp.abilities;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public interface CanHaveChildren {

    GameObject getChild(String name);

    GameObject getChildFromHierarchy(String name);

    boolean contains(String name);

    boolean containsInHierarchy(String name);

    List<GameObject> getChildren();
}
