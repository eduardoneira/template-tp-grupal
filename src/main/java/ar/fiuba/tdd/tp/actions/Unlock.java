package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class Unlock extends ActionHandler {

    private int key;

    private int argsSize = 1;

    public Unlock(GameObject instance, int key) {
        super(instance);
        this.key = key;
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if (objectsInvolved.size() != argsSize) {
            return false;
        }
        return true;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        // cabeza, si alguien tiene esta accion puede unlockear, aunque no hay llamada que lo haga explicitamente
        return "done";
    }

    @Override
    public String getName() {
        return "unlock" + key;
    }
}
