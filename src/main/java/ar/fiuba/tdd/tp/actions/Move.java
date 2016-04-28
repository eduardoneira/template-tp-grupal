package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.*;
import java.util.List;

public class Move extends ActionHandler {

    protected int OBJECT_TO_MOVE = 0;
    protected int WHERE_TO_MOVE = 1;
    protected int ARGS_SIZE = 2;
    private String BE_MOVED = "be moved";
    private String HAVE_MOVED_TO = "have moved to";
    protected ConcreteGameObjectWithParentAndChildren instance;

    public Move(ConcreteGameObjectWithParentAndChildren instance) {
        super(instance);
        this.instance = instance;
        actionsCaused.add(BE_MOVED);
        actionsCaused.add(HAVE_MOVED_TO);
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

        objectToMove.handleAction(BE_MOVED, objectsInvolvedForObjectToMove);
        whereToMove.handleAction(HAVE_MOVED_TO, objectsInvolvedForWhereToMove);

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

        return objectToMove.canHandleAction(BE_MOVED, objectsInvolvedForObjectToMove)
                && whereToMove.canHandleAction(HAVE_MOVED_TO, objectsInvolvedForWhereToMove);
    }

    @Override
    public String getName() {
        return "move";
    }
}
