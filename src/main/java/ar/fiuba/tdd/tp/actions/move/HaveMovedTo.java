package ar.fiuba.tdd.tp.actions.move;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.List;

public class HaveMovedTo extends ActionHandler {

    private static int WHO_MOVES = 0;
    private static int OBJECT_TO_MOVE = 1;
    private static int ARGS_SIZE = 2;

    ChildrenState children;
    GameObject instance;

    public HaveMovedTo(GameObject instance, ChildrenState children) {
        super(instance);
        this.children = children;
    }


    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        return objectsInvolved.size() == ARGS_SIZE;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        GameObject objectToMove = objectsInvolved.get(OBJECT_TO_MOVE);
        children.addChild(objectToMove);
        return "done";
    }

    @Override
    public String getName() {
        return "have moved to";
    }
}
