package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ParentState;

import java.util.LinkedList;
import java.util.List;

public class BeMoved extends ActionHandler {

    private int idWhereToMove = 1;
    private int argsSize = 2;

    ParentState parent;

    public BeMoved(GameObject instance, ParentState parent) {
        super(instance);
        this.parent = parent;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        GameObject whereToMove = objectsInvolved.get(idWhereToMove);

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
        if (objectsInvolved.size() != argsSize
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
