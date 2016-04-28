package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.List;

public class HaveMovedFrom extends ActionHandler {

    private int idObjectToMove = 0;
    private int argsSize = 1;

    protected ChildrenState children;

    public HaveMovedFrom(GameObject instance, ChildrenState children) {
        super(instance);
        this.children = children;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        GameObject objectToMove = objectsInvolved.get(idObjectToMove);
        children.removeChild(objectToMove);
        return "done";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {

        GameObject objectToMove = objectsInvolved.get(idObjectToMove);
        return objectsInvolved.size() == argsSize && children.contains(objectToMove.getName());
    }

    @Override
    public String getName() {
        return "have moved from";
    }
}
