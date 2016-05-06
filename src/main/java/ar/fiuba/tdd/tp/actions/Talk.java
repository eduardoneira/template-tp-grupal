package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;

public class Talk extends ActionHandler {

    private static final int idObjectToTalk = 0;
    //private int argsSize = 1;
    private static final String beTalkedTo = "be talked to";

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        return objectsInvolved.size() == argsSize && conversating(this.instance, objectsInvolved.get(idObjectToTalk), response);
    }

    @Override
    public String getName() {
        return "talk";
    }

    private boolean conversating(GameObject objectWhoTalk, GameObject objectToTalk, StringBuilder response) {
        List<GameObject> objectsInvolvedForObjectToTalk = new LinkedList<>();
        objectsInvolvedForObjectToTalk.add(objectWhoTalk);

        return objectToTalk.canHandleAction(beTalkedTo, objectsInvolvedForObjectToTalk, response);
    }

    public Talk(GameObject instance) {
        super(instance, 1);
        actionsCaused.add(beTalkedTo);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        /*StringBuilder response = new StringBuilder();
        if (!canHandleAction(actionName, objectsInvolved, response)) {
            return response.toString();
        }*/

        GameObject objectToTalk = objectsInvolved.get(idObjectToTalk);
        GameObject objectWhoTalks = this.instance;

        List<GameObject> objectsInvolvedForObjectToTalk = new LinkedList<>();
        objectsInvolvedForObjectToTalk.add(objectWhoTalks);

        return objectToTalk.handleAction(beTalkedTo, objectsInvolvedForObjectToTalk);
    }
}
