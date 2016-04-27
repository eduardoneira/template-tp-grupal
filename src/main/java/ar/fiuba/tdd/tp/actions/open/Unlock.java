package ar.fiuba.tdd.tp.actions.open;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

/**
 * Created by Master on 27/04/2016.
 */
public class Unlock extends ActionHandler {

    private int key;

    private static int OBJECT_TO_UNLOCK = 0;
    private static int ARGS_SIZE = 1;

    public Unlock(GameObject instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        GameObject objectToUnlock = objectsInvolved.get(OBJECT_TO_UNLOCK);
        return objectToUnlock.handleAction("be unlocked", objectsInvolved);
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if(objectsInvolved.size() != ARGS_SIZE) {
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return "unlock" + key;
    }
}
