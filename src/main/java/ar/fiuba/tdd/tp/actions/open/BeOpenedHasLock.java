package ar.fiuba.tdd.tp.actions.open;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Master on 27/04/2016.
 */
public class BeOpenedHasLock extends BeOpened {

    private static int OBJECT_THAT_UNLOCKS_ME = 0;

    private BooleanState locked;
    private int key;

    public BeOpenedHasLock(GameObject instance, BooleanState open, BooleanState locked) {
        super(instance, open);
        this.locked = locked;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        return super.handleAction(actionName, objectsInvolved);
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if(!super.canIHandleAction(objectsInvolved)) {
            return false;
        }

        if(locked.getValue()) {
            GameObject objectThatUnlocksMe = objectsInvolved.get(OBJECT_THAT_UNLOCKS_ME);

            List<GameObject> objectsInvolvedForUnlocker = new LinkedList<GameObject>();
            objectsInvolvedForUnlocker.add(this.instance);

            return objectThatUnlocksMe.canHandleAction("unlock" + key, objectsInvolvedForUnlocker);
        }

        return true;
    }
}
