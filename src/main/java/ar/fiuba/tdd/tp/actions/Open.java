package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;

public class Open extends ActionHandler {

    private int idObjectToOpen = 0;
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
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        if (objectsInvolved.size() != argsSize) {
            setResponseError(objectsInvolved, response);
            return false;
        }

        GameObject objectWhoOpens = this.instance;
        List<GameObject> objectsInvolvedForObjectToOpen = new LinkedList<GameObject>();
        GameObject objectToOpen = objectsInvolved.get(idObjectToOpen);

        objectsInvolvedForObjectToOpen.add(objectWhoOpens);

        return objectToOpen.canHandleAction(beOpened, objectsInvolvedForObjectToOpen, response);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        StringBuilder response = new StringBuilder();
        if (!canHandleAction(actionName, objectsInvolved, response)) {
            return response.toString();
        }

        GameObject objectToOpen = objectsInvolved.get(idObjectToOpen);
        List<GameObject> objectsInvolvedForObjectToOpen = new LinkedList<GameObject>();
        GameObject objectWhoOpens = this.instance;

        objectsInvolvedForObjectToOpen.add(objectWhoOpens);

        objectToOpen.handleAction(beOpened, objectsInvolvedForObjectToOpen);

        return "opened " + objectToOpen.getName();
    }
}
