package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.general.*;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

public class Player extends ConcreteGameObjectWithParentAndChildren {

    public Player(String name, GameObjectWithChildren parent) {
        this(name, parent, new ChildrenState());
    }

    public Player(String name, GameObjectWithChildren parent, ChildrenState children) {
        super(name, parent, children);
        addAction(new Look(this));
        addAction(new BeMoved(this, this.parent));
        addAction(new HaveMovedTo(this, this.children));
        addAction(new What(this));
        addAction(new BeLookedAt(this));
    }
}
