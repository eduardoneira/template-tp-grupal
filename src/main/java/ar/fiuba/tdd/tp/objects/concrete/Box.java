package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.objects.general.*;
import ar.fiuba.tdd.tp.objects.states.ChildrenStateLimitedSize;

public class Box extends Container {

    public Box(String name, GameObjectWithChildren parent) {
        super(name, parent, new ChildrenStateLimitedSize(1));
    }
}
