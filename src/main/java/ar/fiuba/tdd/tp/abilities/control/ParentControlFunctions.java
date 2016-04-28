package ar.fiuba.tdd.tp.abilities.control;

import ar.fiuba.tdd.tp.abilities.CanHaveParent;
import ar.fiuba.tdd.tp.objects.general.GameObject;

public interface ParentControlFunctions extends CanHaveParent {
    void setParent(GameObject parent);
}
