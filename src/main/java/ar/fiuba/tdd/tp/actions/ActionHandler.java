package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public abstract class ActionHandler {
    protected GameObject instance;

    public ActionHandler(GameObject instance) {
        this.instance = instance;
    }

    public abstract String handleAction(String actionName, List<GameObject> objectsInvolved);

    public boolean canHandleAction(String actionName, List<GameObject> objectsInvolved) {
        if (!actionName.equals(this.getName())) {
            return false;
        }
        return canIHandleAction(objectsInvolved);
    }

    protected abstract  boolean canIHandleAction(List<GameObject> objectsInvolved);

    public abstract String getName();
}
