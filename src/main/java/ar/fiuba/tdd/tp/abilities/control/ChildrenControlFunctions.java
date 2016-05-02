package ar.fiuba.tdd.tp.abilities.control;


import ar.fiuba.tdd.tp.abilities.CanHaveChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithParent;

public interface ChildrenControlFunctions extends CanHaveChildren {

    void addChild(GameObjectWithParent child);

    void removeChild(GameObjectWithParent child);

    boolean isEmpty();
}
