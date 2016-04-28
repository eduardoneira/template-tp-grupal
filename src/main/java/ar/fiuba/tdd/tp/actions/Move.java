package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithChildren;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.*;
import java.util.List;

public class Move extends ActionHandler {

    private int OBJECT_TO_MOVE = 0;
    private int WHERE_TO_MOVE = 1;
    private int ARGS_SIZE = 2;
    protected ConcreteGameObjectWithParentAndChildren instance;

    public Move(ConcreteGameObjectWithParentAndChildren instance) {
        super(instance);
        this.instance = instance;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        if (!canHandleAction(actionName, objectsInvolved)) {
            return "invalid command";
        }

        List<GameObject> objectsInvolvedForObjectToMove = new LinkedList<GameObject>();
        List<GameObject> objectsInvolvedForWhereToMove = new LinkedList<GameObject>();
        objectsInvolvedForObjectToMove.add(this.instance);
        objectsInvolvedForWhereToMove.add(this.instance);
        GameObject whereToMove = objectsInvolved.get(WHERE_TO_MOVE);
        GameObject objectToMove = objectsInvolved.get(OBJECT_TO_MOVE);
        objectsInvolvedForObjectToMove.add(whereToMove);
        objectsInvolvedForWhereToMove.add(objectToMove);

        objectToMove.handleAction("be moved", objectsInvolvedForObjectToMove);
        whereToMove.handleAction("have moved to", objectsInvolvedForWhereToMove);

        return "moved " + objectToMove.getName() + " to " + whereToMove.getName();
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if (objectsInvolved.size() != ARGS_SIZE) {
            return false;
        }

        GameObject whereToMove = objectsInvolved.get(WHERE_TO_MOVE);
        List<GameObject> objectsInvolvedForObjectToMove = new LinkedList<GameObject>();
        objectsInvolvedForObjectToMove.add(this.instance);
        objectsInvolvedForObjectToMove.add(whereToMove);

        GameObject objectToMove = objectsInvolved.get(OBJECT_TO_MOVE);
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
