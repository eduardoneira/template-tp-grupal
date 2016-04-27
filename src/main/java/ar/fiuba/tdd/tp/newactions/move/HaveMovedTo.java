package ar.fiuba.tdd.tp.newactions.move;

import ar.fiuba.tdd.tp.newactions.ActionHandler;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.List;

/**
 * Created by Master on 27/04/2016.
 */
public class HaveMovedTo extends ActionHandler {

    private static int WHO_MOVES = 0;
    private static int OBJECT_TO_MOVE = 1;
    private static int ARGS_SIZE = 2;

    ChildrenState children;
    GameObject instance;

    public HaveMovedTo(GameObject instance) {
        super(instance);
        children = new ChildrenState();
    }

    public HaveMovedTo(GameObject instance, ChildrenState children) {
        super(instance);
        this.children = children;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        GameObject objectToMove = objectsInvolved.get(OBJECT_TO_MOVE);
        children.addChild(objectToMove);
        return "done";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        return objectsInvolved.size() == ARGS_SIZE;
    }

    @Override
    public String getName() {
        return "have moved to";
    }
}
