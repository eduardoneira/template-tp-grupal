package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithParent;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.List;

public class HaveMovedFrom extends ActionHandler {

    protected int idObjectToMove = 0;
    protected int argsSize = 1;

    protected ChildrenState children;

    public HaveMovedFrom(GameObject instance, ChildrenState children) {
        super(instance);
        this.children = children;
    }


    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {

        GameObject objectToMove = objectsInvolved.get(idObjectToMove);
        if (!(objectsInvolved.size() == argsSize && children.contains(objectToMove.getName()))) {
            setResponseError(objectsInvolved, response);
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return "have moved from";
    }


    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        GameObjectWithParent objectToMove = (GameObjectWithParent) objectsInvolved.get(idObjectToMove);
        children.removeChild(objectToMove);
        return "done";
    }

}
