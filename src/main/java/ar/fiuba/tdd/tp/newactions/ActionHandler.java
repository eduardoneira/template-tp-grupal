package ar.fiuba.tdd.tp.newactions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Master on 27/04/2016.
 */
public abstract class ActionHandler {
    protected GameObject instance;

    public ActionHandler(GameObject instance){
        this.instance = instance;
    }

    abstract public String handleAction(String actionName, List<GameObject> objectsInvolved);

    protected boolean canHandleAction(String actionName, List<GameObject> objectsInvolved) {
        if(actionName != getName()) {
            return false;
        }
        return canIHandleAction(objectsInvolved);
    }

    abstract protected boolean canIHandleAction(List<GameObject> objectsInvolved);

    abstract public String getName();
}
