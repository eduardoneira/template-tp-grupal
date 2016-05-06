package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;

public abstract class ActionHandler {

    protected GameObject instance;
    protected final List<String> actionsCaused;
    protected final int argsSize;

    public ActionHandler(GameObject instance, int argsSize) {
        this.instance = instance;
        actionsCaused = new LinkedList<>();
        this.argsSize = argsSize;
    }

    /*public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        StringBuilder response = new StringBuilder();
        if (!canHandleAction(actionName, objectsInvolved, response)) {
            return response.toString();
        }
        return handleAction(actionName, objectsInvolved);
    }*/

    public abstract String handleAction(String actionName, List<GameObject> objectsInvolved);

    public boolean canHandleAction(String actionName, List<GameObject> objectsInvolved, StringBuilder response) {
        if (!actionName.equals(this.getName())) {
            response.append("command name check failed");
            return false;
        }
        if (objectsInvolved.size() != argsSize) {
            setResponseError(objectsInvolved, response);
            return false;
        }
        //return true;
        return canIHandleAction(objectsInvolved, response);
    }

    protected abstract boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response);

    public abstract String getName();

    protected void setResponseError(List<GameObject> objectsInvolved, StringBuilder response) {
        response.append("command ");
        response.append(getName());
        response.append(" in ");
        response.append(this.instance.getName());
        response.append(" can't handle arguments:");
        if (objectsInvolved.size() == 0) {
            response.append(" EMPTY");
        }
        for (GameObject object : objectsInvolved) {
            response.append(" ");
            response.append(object.getName());
        }
    }

    // para what
    public boolean causes(String actionName) {
        return actionsCaused.contains(actionName);
    }

    protected String concatSentences(String... sentences) {

        if (sentences.length == 0) {
            return "";
        }

        StringBuilder concatenated = new StringBuilder();
        concatenated.append(sentences[0]);
        for (int i = 1; i < sentences.length; ++i) {
            if ((sentences[i]).equals("")) {
                continue;
            }
            concatenated.append(". ");
            concatenated.append(sentences[i]);
        }

        return concatenated.toString();
    }
}
