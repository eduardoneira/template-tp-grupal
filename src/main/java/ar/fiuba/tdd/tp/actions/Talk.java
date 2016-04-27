package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import java.util.LinkedList;
import java.util.List;

public class Talk extends ActionHandler {
    private static int OBJECT_TO_TALK = 0;
    private static int ARGS_SIZE = 1;

    public Talk(GameObject instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        if (!canHandleAction(actionName, objectsInvolved)) {
            return "invalid command";
        }

        GameObject objectToTalk = objectsInvolved.get(OBJECT_TO_TALK);
        GameObject objectWhoTalks = this.instance;

        List<GameObject> objectsInvolvedForObjectToTalk = new LinkedList<GameObject>();
        objectsInvolvedForObjectToTalk.add(objectWhoTalks);

        objectToTalk.handleAction("be talk", objectsInvolved);

        return "opened " + objectToTalk.getName();


    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if (objectsInvolved.size() != ARGS_SIZE) {
            return false;
        }

        GameObject objectToTalk = objectsInvolved.get(OBJECT_TO_TALK);
        GameObject objectWhoTalk = this.instance;

        List<GameObject> objectsInvolvedForObjectToTalk = new LinkedList<GameObject>();
        objectsInvolvedForObjectToTalk.add(objectWhoTalk);

        return objectToTalk.canHandleAction("be talk", objectsInvolvedForObjectToTalk);
    }

    @Override
    public String getName() {
        return "talk";
    }
}
