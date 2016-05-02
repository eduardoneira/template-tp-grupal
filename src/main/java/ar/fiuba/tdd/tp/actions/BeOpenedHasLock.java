package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.LinkedList;
import java.util.List;

public class BeOpenedHasLock extends BeOpened {

    private int idObjectThatUnlocksMe = 0;

    private BooleanState locked;
    private int key;

    public BeOpenedHasLock(GameObject instance, BooleanState open, BooleanState locked, int key) {
        super(instance, open);
        this.locked = locked;
        this.key = key;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        // si llego aca es que puede deslockearme, aunque nunca llame a una accion que lo haga posta (pq no existe 'unlock' para el usuario)
        locked.setFalse();
        return super.handleAction(actionName, objectsInvolved);
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        if (!super.canIHandleAction(objectsInvolved, response)) {
            return false;
        }

        if (locked.getValue()) {
            GameObject objectThatUnlocksMe = objectsInvolved.get(idObjectThatUnlocksMe);

            List<GameObject> objectsInvolvedForUnlocker = new LinkedList<GameObject>();
            objectsInvolvedForUnlocker.add(this.instance);

            return objectThatUnlocksMe.canHandleAction("unlock" + key, objectsInvolvedForUnlocker, response);
        }

        return true;
    }
}
