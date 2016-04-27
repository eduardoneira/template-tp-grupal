package ar.fiuba.tdd.tp.abilities.control;


import ar.fiuba.tdd.tp.abilities.CanHaveChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveParent;

public interface ChildrenControlFunctions extends CanHaveChildren {

    void addChild(GameObject child);

    void removeChild(GameObject child);
}
