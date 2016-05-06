package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class Unlock extends ActionHandler {

    private final int key;

    //private int argsSize = 1;

    public Unlock(GameObject instance, int key) {
        super(instance, 1);
        this.key = key;
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        /*if (objectsInvolved.size() != argsSize) {
            setResponseError(objectsInvolved, response);
            return false;
        }*/
        return true;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        // cabeza, si alguien tiene esta accion puede unlockear, aunque no hay llamada que lo haga explicitamente
        return "";
    }

    @Override
    public String getName() {
        return "unlock" + key;
    }
}
