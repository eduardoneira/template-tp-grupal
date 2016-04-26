package ar.fiuba.tdd.tp.abilities;


import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveChildren;

public interface CanHaveParentPrivate extends CanHaveParent {
    void setParent(GameObjectCanHaveChildren parent);
}
