package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.abilities.control.ParentControlFunctions;
import ar.fiuba.tdd.tp.newactions.move.HaveMovedFrom;
import ar.fiuba.tdd.tp.newactions.move.HaveMovedTo;
import ar.fiuba.tdd.tp.objects.general.*;
import ar.fiuba.tdd.tp.objects.states.ParentState;

public class Box extends ConcreteGameObjectWithParentAndChildren {

    public Box(String name) {
        super(name);

        addAction(new HaveMovedFrom(this, children));
        addAction(new HaveMovedTo(this, children));
    }
}
