package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.*;
import java.util.List;

public class Move extends ActionHandler {

    protected int idObjectToMove = 0;
    protected int idWhereToMove = 1;
    protected int argsSize = 2;
    private String beMoved = "be moved";
    private String haveMovedTo = "have moved to";

    public Move(GameObject instance) {
        super(instance);
        actionsCaused.add(beMoved);
        //actionsCaused.add(haveMovedTo);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        StringBuilder response = new StringBuilder();
        if (!canHandleAction(actionName, objectsInvolved, response)) {
            return response.toString();
        }

        List<GameObject> objectsInvolvedForObjectToMove = new LinkedList<GameObject>();
        List<GameObject> objectsInvolvedForWhereToMove = new LinkedList<GameObject>();
        objectsInvolvedForObjectToMove.add(this.instance);
        objectsInvolvedForWhereToMove.add(this.instance);
        GameObject whereToMove = objectsInvolved.get(idWhereToMove);
        GameObject objectToMove = objectsInvolved.get(idObjectToMove);
        objectsInvolvedForObjectToMove.add(whereToMove);
        objectsInvolvedForWhereToMove.add(objectToMove);

        objectToMove.handleAction(beMoved, objectsInvolvedForObjectToMove);
        whereToMove.handleAction(haveMovedTo, objectsInvolvedForWhereToMove);

        return "moved " + objectToMove.getName() + " to " + whereToMove.getName();
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        if (objectsInvolved.size() != argsSize) {
            setResponseError(objectsInvolved, response);
            return false;
        }

        GameObject whereToMove = objectsInvolved.get(idWhereToMove);
        List<GameObject> objectsInvolvedForObjectToMove = new LinkedList<GameObject>();
        objectsInvolvedForObjectToMove.add(this.instance);
        objectsInvolvedForObjectToMove.add(whereToMove);

        GameObject objectToMove = objectsInvolved.get(idObjectToMove);
        List<GameObject> objectsInvolvedForWhereToMove = new LinkedList<GameObject>();
        objectsInvolvedForWhereToMove.add(this.instance);
        objectsInvolvedForWhereToMove.add(objectToMove);
        return objectToMove.canHandleAction(beMoved, objectsInvolvedForObjectToMove, response)
                && whereToMove.canHandleAction(haveMovedTo, objectsInvolvedForWhereToMove, response);
    }

    @Override
    public String getName() {
        return "move";
    }
}
