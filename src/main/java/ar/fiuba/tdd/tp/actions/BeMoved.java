package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;
import ar.fiuba.tdd.tp.objects.states.ParentState;

import java.util.LinkedList;
import java.util.List;

public class BeMoved extends ActionHandler {

    private static final int idWhereToMove = 1;
    //private int argsSize = 2;

    final ParentState parent;

    public BeMoved(GameObject instance, ParentState parent) {
        super(instance, 2);
        this.parent = parent;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        GameObjectWithChildren whereToMove = (GameObjectWithChildren) objectsInvolved.get(idWhereToMove);

        List<GameObject> me = new LinkedList<>();
        me.add(this.instance);

        // me borro del padre
        if (this.parent.getParent() != null) {
            this.parent.getParent().handleAction("have moved from", new LinkedList<>(me));
        }

        // me guardo nuevo padre
        this.parent.setParent(whereToMove);
        return "";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        List<GameObject> me = new LinkedList<>();
        me.add(this.instance);

        GameObjectWithChildren whereToMove = (GameObjectWithChildren) objectsInvolved.get(idWhereToMove);
        if (!this.parent.canBeChangedTo(whereToMove)) {
            response.append(this.instance.getName());
            response.append(" will not be moved to ");
            response.append(whereToMove.getName());
            return false;
        } else {
            // tal vez pasarle quien mueve
            if (this.parent.getParent() != null && (!this.parent.getParent().canHandleAction("have moved from", me, response))) {
                return false;
            }

            if (this.parent.getParent() != null && this.parent.getParent().equals(whereToMove)) {
                response.append(this.instance.getName());
                response.append(" is already in ");
                response.append(whereToMove.getName());
                return false;
            }

            return true;
        }
    }

    @Override
    public String getName() {
        return "be moved";
    }
}
