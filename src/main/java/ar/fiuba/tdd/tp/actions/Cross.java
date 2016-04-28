package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ParentState;

import java.util.LinkedList;
import java.util.List;

public class Cross extends BeMoved {

    private int WHERE_TO_CROSS = 0;
    private int ARGS_SIZE = 1;

    public Cross(GameObject instance, ParentState parent) {
        super(instance, parent);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        GameObject whereToCross = objectsInvolved.get(WHERE_TO_CROSS);
        List<GameObject> objectsInvolvedForObjectToCross = new LinkedList<GameObject>();
        objectsInvolvedForObjectToCross.add(this.instance);
        objectsInvolvedForObjectToCross.add(whereToCross);
        String res = super.handleAction(actionName, objectsInvolvedForObjectToCross);
        if (res == "invalid command") {
            return "invalid command";
        }
        return "crossed";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        return true;
    }

    @Override
    public String getName() {
        return "cross";
    }
}
