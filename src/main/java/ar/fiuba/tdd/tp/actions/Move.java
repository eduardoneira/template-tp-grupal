package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.*;
import java.util.List;

public class Move extends ActionHandler {

    protected static final int idObjectToMove = 0;
    protected static final int idWhereToMove = 1;
    //protected int argsSize = 2;
    private static final String beMoved = "be moved";
    private static final String haveMovedTo = "have moved to";

    public Move(GameObject instance) {
        super(instance, 2);
        actionsCaused.add(beMoved);
        //actionsCaused.add(haveMovedTo);
    }

    public Move(GameObject instance, int argsSize) {
        super(instance, argsSize);
        actionsCaused.add(beMoved);
        //actionsCaused.add(haveMovedTo);
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        /*if (objectsInvolved.size() != argsSize) {
            setResponseError(objectsInvolved, response);
            return false;
        }*/

        GameObject whereToMove = objectsInvolved.get(idWhereToMove);
        List<GameObject> objectsInvolvedForObjectToMove = new LinkedList<>();
        objectsInvolvedForObjectToMove.add(this.instance);
        objectsInvolvedForObjectToMove.add(whereToMove);

        GameObject objectToMove = objectsInvolved.get(idObjectToMove);
        List<GameObject> objectsInvolvedForWhereToMove = new LinkedList<>();
        objectsInvolvedForWhereToMove.add(this.instance);
        objectsInvolvedForWhereToMove.add(objectToMove);
        return objectToMove.canHandleAction(beMoved, objectsInvolvedForObjectToMove, response)
                && whereToMove.canHandleAction(haveMovedTo, objectsInvolvedForWhereToMove, response);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        /*StringBuilder response = new StringBuilder();
        if (!canHandleAction(actionName, objectsInvolved, response)) {
            return response.toString();
        }*/

        List<GameObject> objectsInvolvedForObjectToMove = new LinkedList<>();
        List<GameObject> objectsInvolvedForWhereToMove = new LinkedList<>();
        objectsInvolvedForObjectToMove.add(this.instance);
        objectsInvolvedForWhereToMove.add(this.instance);
        GameObject whereToMove = objectsInvolved.get(idWhereToMove);
        GameObject objectToMove = objectsInvolved.get(idObjectToMove);
        objectsInvolvedForObjectToMove.add(whereToMove);
        objectsInvolvedForWhereToMove.add(objectToMove);

        String retFromToMove = objectToMove.handleAction(beMoved, objectsInvolvedForObjectToMove);
        String retFromWhereToMove = whereToMove.handleAction(haveMovedTo, objectsInvolvedForWhereToMove);

        return concatSentences(successMessage(objectsInvolved), retFromToMove, retFromWhereToMove);
        //return successMessage(objectsInvolved) + retFromToMove + retFromWhereToMove;
    }

    protected String successMessage(List<GameObject> objectsInvolved) {
        GameObject whereToMove = objectsInvolved.get(idWhereToMove);
        GameObject objectToMove = objectsInvolved.get(idObjectToMove);
        return "moved " + objectToMove.getName() + " to " + whereToMove.getName();
    }

    @Override
    public String getName() {
        return "move";
    }
}
