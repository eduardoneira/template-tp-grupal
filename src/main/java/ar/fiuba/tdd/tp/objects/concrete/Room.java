package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.BeLookedAtAndChildren;
import ar.fiuba.tdd.tp.actions.HaveMovedFrom;
import ar.fiuba.tdd.tp.actions.HaveMovedTo;
import ar.fiuba.tdd.tp.objects.general.*;

public class Room extends ConcreteGameObjectWithChildren {

    public Room(String name) {
        super(name);
        addAction(new HaveMovedTo(this, children));
        addAction(new HaveMovedFrom(this, children));
        addAction(new BeLookedAtAndChildren(this, children));
    }
}
