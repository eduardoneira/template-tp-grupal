package ar.fiuba.tdd.tp.actions.move;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.List;

public class HaveMovedFrom extends ActionHandler {

    private static int OBJECT_TO_MOVE = 0;
    private static int ARGS_SIZE = 1;

    private ChildrenState children;

    public HaveMovedFrom(GameObject instance, ChildrenState children) {
        super(instance);
        this.children = children;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        GameObject objectToMove = objectsInvolved.get(OBJECT_TO_MOVE);
        children.removeChild(objectToMove);
        return "done";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {

        GameObject objectToMove = objectsInvolved.get(OBJECT_TO_MOVE);
        return objectsInvolved.size() == ARGS_SIZE && children.contains(objectToMove.getName());
    }

    @Override
    public String getName() {
        return "have moved from";
    }
}
