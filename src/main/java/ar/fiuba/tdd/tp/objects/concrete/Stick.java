package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.newactions.lookat.BeLookedAt;
import ar.fiuba.tdd.tp.newactions.lookat.BeLookedAtAndChildren;
import ar.fiuba.tdd.tp.newactions.lookat.Look;
import ar.fiuba.tdd.tp.newactions.move.BeMoved;
import ar.fiuba.tdd.tp.objects.general.*;

public class Stick extends ConcreteGameObjectWithParent {

    public Stick(String name) {
        super(name);

        addAction(new BeMoved(this, parent));
        addAction(new BeLookedAt(this));
    }
}
