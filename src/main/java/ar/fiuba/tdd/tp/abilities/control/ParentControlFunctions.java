package ar.fiuba.tdd.tp.abilities.control;

import ar.fiuba.tdd.tp.abilities.CanHaveParent;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;

public interface ParentControlFunctions extends CanHaveParent {
    void setParent(GameObjectWithChildren parent);
}
