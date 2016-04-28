package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.*;
import ar.fiuba.tdd.tp.objects.states.ParentState;

import java.util.List;


public class Cross extends ChangeRoom {

    public Cross(ConcreteGameObjectWithParent instance, ParentState parent) {
        super(instance, parent);
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        //instance.getParent().getObjects()
        /*
        for object in objects():
            object.canBeLeft()
         */
        return super.canIHandleAction(objectsInvolved);
    }

    @Override
    public String getName() {
        return "cross";
    }
}
