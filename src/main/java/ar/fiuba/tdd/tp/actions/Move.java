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
    protected ConcreteGameObjectWithParentAndChildren instance;

    public Move(ConcreteGameObjectWithParentAndChildren instance) {
        super(instance);
        this.instance = instance;
        actionsCaused.add(beMoved);
        actionsCaused.add(haveMovedTo);
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
        GameObject whereToMove = objectsInvolved.get(idWhereToMove);
        GameObject objectToMove = objectsInvolved.get(idObjectToMove);
        objectsInvolvedForObjectToMove.add(whereToMove);
        objectsInvolvedForWhereToMove.add(objectToMove);

        objectToMove.handleAction(beMoved, objectsInvolvedForObjectToMove);
        whereToMove.handleAction(haveMovedTo, objectsInvolvedForWhereToMove);

        return "moved " + objectToMove.getName() + " to " + whereToMove.getName();
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if (objectsInvolved.size() != argsSize) {
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
        return objectToMove.canHandleAction(beMoved, objectsInvolvedForObjectToMove)
                && whereToMove.canHandleAction(haveMovedTo, objectsInvolvedForWhereToMove);
    }

    @Override
    public String getName() {
        return "move";
    }
}
