package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;

public class Talk extends ActionHandler {

    private int idObjectToTalk = 0;
    private int argsSize = 1;
    private String beTalkedTo = "be talked to";

    public Talk(GameObject instance) {
        super(instance);
        actionsCaused.add(beTalkedTo);
    }

    @Override
    public String getName() {
        return "talk";
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        if (!canHandleAction(actionName, objectsInvolved)) {
            return "invalid command";
        }

        GameObject objectToTalk = objectsInvolved.get(idObjectToTalk);
        GameObject objectWhoTalks = this.instance;

        List<GameObject> objectsInvolvedForObjectToTalk = new LinkedList<GameObject>();
        objectsInvolvedForObjectToTalk.add(objectWhoTalks);

        return objectToTalk.handleAction(beTalkedTo, objectsInvolvedForObjectToTalk);
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if (objectsInvolved.size() != argsSize) {
            return false;
        }

        GameObject objectToTalk = objectsInvolved.get(idObjectToTalk);
        GameObject objectWhoTalk = this.instance;

        List<GameObject> objectsInvolvedForObjectToTalk = new LinkedList<GameObject>();
        objectsInvolvedForObjectToTalk.add(objectWhoTalk);

        return objectToTalk.canHandleAction(beTalkedTo, objectsInvolvedForObjectToTalk);
    }
}
