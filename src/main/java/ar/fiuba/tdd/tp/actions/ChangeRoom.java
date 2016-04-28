package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ParentState;

import java.util.LinkedList;
import java.util.List;

public class ChangeRoom extends BeMoved {

    private int idWhereTo = 0;
    private int argsSize = 1;

    public ChangeRoom(GameObject instance, ParentState parent) {
        super(instance, parent);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        GameObject whereToCross = objectsInvolved.get(idWhereTo);
        List<GameObject> objectsInvolvedForObjectToCross = new LinkedList<GameObject>();
        objectsInvolvedForObjectToCross.add(this.instance);
        objectsInvolvedForObjectToCross.add(whereToCross);
        String res = super.handleAction(actionName, objectsInvolvedForObjectToCross);
        if (res == "invalid command") {
            return "invalid command";
        }
        return "changed room";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        GameObject whereToCross = objectsInvolved.get(idWhereTo);
        List<GameObject> objectsInvolvedForObjectToCross = new LinkedList<GameObject>();
        objectsInvolvedForObjectToCross.add(this.instance);
        objectsInvolvedForObjectToCross.add(whereToCross);
        return super.canIHandleAction(objectsInvolvedForObjectToCross);
    }

    @Override
    public String getName() {
        return "change room";
    }
}
