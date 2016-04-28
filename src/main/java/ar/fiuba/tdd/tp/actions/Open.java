package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;

public class Open extends ActionHandler {

    private int OBJECT_TO_OPEN = 0;
    private int ARGS_SIZE = 1;

    public Open(GameObject instance) {
        super(instance);
    }

    @Override
    public String getName() {
        return "open";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if (objectsInvolved.size() != ARGS_SIZE) {
            return false;
        }

        GameObject objectWhoOpens = this.instance;
        List<GameObject> objectsInvolvedForObjectToOpen = new LinkedList<GameObject>();
        GameObject objectToOpen = objectsInvolved.get(OBJECT_TO_OPEN);

        objectsInvolvedForObjectToOpen.add(objectWhoOpens);

        return objectToOpen.canHandleAction("be opened", objectsInvolvedForObjectToOpen);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        if (!canHandleAction(actionName, objectsInvolved)) {
            return "invalid command";
        }

        GameObject objectToOpen = objectsInvolved.get(OBJECT_TO_OPEN);
        List<GameObject> objectsInvolvedForObjectToOpen = new LinkedList<GameObject>();
        GameObject objectWhoOpens = this.instance;

        objectsInvolvedForObjectToOpen.add(objectWhoOpens);

        objectToOpen.handleAction("be opened", objectsInvolved);

        return "opened " + objectToOpen.getName();
    }
}
