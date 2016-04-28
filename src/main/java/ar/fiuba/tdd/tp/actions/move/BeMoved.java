package ar.fiuba.tdd.tp.actions.move;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ParentState;
import java.util.LinkedList;
import java.util.List;

public class BeMoved extends ActionHandler {

    private static int WHO_MOVES = 0;
    private static int WHERE_TO_MOVE = 1;
    private static int ARGS_SIZE = 2;

    ParentState parent;

    public BeMoved(GameObject instance) {
        super(instance);
        parent = new ParentState();
    }

    public BeMoved(GameObject instance, ParentState parent) {
        super(instance);
        this.parent = parent;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        GameObject whoMoves = objectsInvolved.get(WHO_MOVES);
        GameObject whereToMove = objectsInvolved.get(WHERE_TO_MOVE);

        List<GameObject> me = new LinkedList<GameObject>();
        me.add(this.instance);

        // me borro del padre
        if (this.parent.getParent() != null) {
            this.parent.getParent().handleAction("have moved from", new LinkedList<GameObject>(me));
        }

        // me guardo nuevo padre
        this.parent.setParent(whereToMove);
        return "done";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        List<GameObject> me = new LinkedList<GameObject>();
        me.add(this.instance);

        // tal vez pasarle quien mueve
        if (objectsInvolved.size() != ARGS_SIZE
                || (this.parent.getParent() != null && (!this.parent.getParent().canHandleAction("have moved from", me)))) {
            return false;
        }

        return true;
    }

    @Override
    public String getName() {
        return "be moved";
    }
}
