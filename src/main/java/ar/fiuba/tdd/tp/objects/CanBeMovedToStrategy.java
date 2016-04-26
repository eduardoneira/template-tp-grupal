package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanBeMoved;
import ar.fiuba.tdd.tp.actions.CanHaveThingsMovedFrom;
import ar.fiuba.tdd.tp.actions.CanHaveThingsMovedTo;

public class CanBeMovedToStrategy implements MoveToStrategy {
    CanHaveThingsMovedTo parent;
    CanBeMoved instance;

    public CanBeMovedToStrategy(CanBeMoved instance) {
        this.instance = instance;
    }

    public String moveTo(CanHaveThingsMovedTo whereTo) {
        // el requisito para 'parent' es que pueda tener cosas, pero no necesariamente que me deje salir despues
        if(!(this.parent instanceof CanHaveThingsMovedFrom)){
            return "invalid command";
        }
        ((CanHaveThingsMovedFrom)this.parent).haveMovedFrom(instance);
        this.parent = whereTo;
        return whereTo.haveMovedTo(instance);
    }

    public void setParent(CanHaveThingsMovedTo parent) {
        this.parent = parent;
    }
}
