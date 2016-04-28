package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.BeTalkedToThief;
import ar.fiuba.tdd.tp.actions.HaveMovedTo;
import ar.fiuba.tdd.tp.actions.Move;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;

public class Thief extends ConcreteGameObjectWithParentAndChildren {

    public Thief(String name) {
        super(name);

        addAction(new BeTalkedToThief(this));
        addAction(new HaveMovedTo(this, children));
        addAction(new Move(this));
    }
}
