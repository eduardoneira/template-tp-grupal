package ar.fiuba.tdd.tp.abilities;


import ar.fiuba.tdd.tp.objects.general.GameObjectCanHaveParent;

public interface CanHaveChildrenPrivate extends CanHaveChildren {

    void addChild(GameObjectCanHaveParent child);

    void removeChild(GameObjectCanHaveParent child);
}
