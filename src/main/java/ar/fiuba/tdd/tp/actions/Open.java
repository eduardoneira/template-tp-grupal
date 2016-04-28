package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;

public class Open extends ActionHandler {

    private int object_To_Open = 0;
    private int argsSize = 1;
    private String beOpened = "be opened";

    public Open(GameObject instance) {
        super(instance);
        actionsCaused.add(beOpened);
    }

    @Override
    public String getName() {
        return "open";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if (objectsInvolved.size() != argsSize) {
            return false;
        }

        GameObject objectWhoOpens = this.instance;
        List<GameObject> objectsInvolvedForObjectToOpen = new LinkedList<GameObject>();
        GameObject objectToOpen = objectsInvolved.get(object_To_Open);

        objectsInvolvedForObjectToOpen.add(objectWhoOpens);

        return objectToOpen.canHandleAction(beOpened, objectsInvolvedForObjectToOpen);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        if (!canHandleAction(actionName, objectsInvolved)) {
            return "invalid command";
        }

        GameObject objectToOpen = objectsInvolved.get(object_To_Open);
        List<GameObject> objectsInvolvedForObjectToOpen = new LinkedList<GameObject>();
        GameObject objectWhoOpens = this.instance;

        objectsInvolvedForObjectToOpen.add(objectWhoOpens);

        objectToOpen.handleAction(beOpened, objectsInvolved);

        return "opened " + objectToOpen.getName();
    }
}
