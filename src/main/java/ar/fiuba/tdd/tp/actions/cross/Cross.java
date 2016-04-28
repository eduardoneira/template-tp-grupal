package ar.fiuba.tdd.tp.actions.cross;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;

public class Cross extends ActionHandler {

    public Cross(GameObject instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
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
