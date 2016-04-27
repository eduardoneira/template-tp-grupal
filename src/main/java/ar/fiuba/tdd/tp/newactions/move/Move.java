package ar.fiuba.tdd.tp.newactions.move;

import ar.fiuba.tdd.tp.newactions.ActionHandler;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Master on 27/04/2016.
 */
public class Move extends ActionHandler {

    private static int OBJECT_TO_MOVE = 0;
    private static int WHERE_TO_MOVE = 1;
    private static int ARGS_SIZE = 2;

    public Move(GameObject instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        if(!canHandleAction(actionName, objectsInvolved)){
            return "invalid command";
        }

        GameObject objectToMove = objectsInvolved.get(OBJECT_TO_MOVE);
        GameObject whereToMove = objectsInvolved.get(WHERE_TO_MOVE);

        List<GameObject> objectsInvolvedForObjectToMove = new LinkedList<GameObject>();
        objectsInvolvedForObjectToMove.add(this.instance);
        objectsInvolvedForObjectToMove.add(whereToMove);

        List<GameObject> objectsInvolvedForWhereToMove = new LinkedList<GameObject>();
        objectsInvolvedForWhereToMove.add(this.instance);
        objectsInvolvedForWhereToMove.add(objectToMove);

        objectToMove.handleAction("be moved", objectsInvolvedForObjectToMove);
        whereToMove.handleAction("have moved to", objectsInvolvedForWhereToMove);

        return "moved " + objectToMove.getName() + " to " + whereToMove.getName();
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if(objectsInvolved.size() != ARGS_SIZE) {
            return false;
        }

        GameObject objectToMove = objectsInvolved.get(OBJECT_TO_MOVE);
        GameObject whereToMove = objectsInvolved.get(WHERE_TO_MOVE);

        List<GameObject> objectsInvolvedForObjectToMove = new LinkedList<GameObject>();
        objectsInvolvedForObjectToMove.add(this.instance);
        objectsInvolvedForObjectToMove.add(whereToMove);

        List<GameObject> objectsInvolvedForWhereToMove = new LinkedList<GameObject>();
        objectsInvolvedForWhereToMove.add(this.instance);
        objectsInvolvedForWhereToMove.add(objectToMove);

        return objectToMove.canHandleAction("be moved", objectsInvolvedForObjectToMove)
                && whereToMove.canHandleAction("have moved to", objectsInvolvedForWhereToMove);
    }

    @Override
    public String getName() {
        return "move";
    }
}
