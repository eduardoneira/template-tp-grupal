package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.general.*;

import java.util.LinkedList;
import java.util.List;

public class Key extends ConcreteGameObjectWithParent {

    protected int key;
    protected List<ActionHandler> actionsGranted;

    public Key(String name, int key) {
        super(name);
        this.key = key;
        //addAction(new ActionAddsActionToSomeone(this, new BeMoved(this, parent), new Unlock(this, key)));
        actionsGranted = new LinkedList<>();
        actionsGranted.add(new Unlock(this, this.key));
        addAction(new BeMovedGrantsActions(this, parent, actionsGranted));
    }
}
