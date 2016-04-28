package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.move.HaveMovedTo;
import ar.fiuba.tdd.tp.actions.talk.BeTalkedToThief;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;

/**
 * Created by Master on 27/04/2016.
 */
public class Thief extends ConcreteGameObjectWithParentAndChildren {

    public Thief(String name) {
        super(name);

        addAction(new BeTalkedToThief(this));
        addAction(new HaveMovedTo(this, children));
    }
}
