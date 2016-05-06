package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;

public class Thief extends ConcreteGameObjectWithParentAndChildren {

    public Thief(String name, GameObjectWithChildren parent) {
        super(name, parent);

        addAction(new BeTalkedToThief(this));
        addAction(new HaveMovedTo(this, children));
        addAction(new Move(this));
        addAction(new BeAskedWhat(this));
        addAction(new BeLookedAt(this));
    }
}
