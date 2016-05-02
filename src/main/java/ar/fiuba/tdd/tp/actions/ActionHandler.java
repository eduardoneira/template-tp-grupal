package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;

public abstract class ActionHandler {

    protected GameObject instance;
    protected List<String> actionsCaused;

    public ActionHandler(GameObject instance) {
        this.instance = instance;
        actionsCaused = new LinkedList<>();
    }

    public abstract String handleAction(String actionName, List<GameObject> objectsInvolved);

    public boolean canHandleAction(String actionName, List<GameObject> objectsInvolved, StringBuilder response) {
        if (!actionName.equals(this.getName())) {
            response.append("command name check failed");
            return false;
        }
        return canIHandleAction(objectsInvolved, response);
    }

    protected abstract boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response);

    public abstract String getName();

    protected void setResponseError(List<GameObject> objectsInvolved, StringBuilder response) {
        response.append("command ");
        response.append(getName());
        response.append(" in ");
        response.append(this.instance.getName());
        response.append(" can't handle arguments:");
        if (objectsInvolved.size() == 0) {
            response.append(" EMPTY");
        }
        for (GameObject object : objectsInvolved) {
            response.append(" ");
            response.append(object.getName());
        }
    }

    // para what
    public boolean causes(String actionName) {
        if (actionsCaused.contains(actionName)) {
            return true;
        }
        return false;
    }
}
