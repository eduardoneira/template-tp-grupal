package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithParent;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.List;

public class HaveMovedFrom extends ActionHandler {

    protected static final int idObjectToMove = 0;
    //protected int argsSize = 1;

    protected final ChildrenState children;

    public HaveMovedFrom(GameObject instance, ChildrenState children) {
        super(instance, 1);
        this.children = children;
    }


    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {

        GameObject objectToMove = objectsInvolved.get(idObjectToMove);
        if (!children.canRemoveChild(objectToMove.getName())) {
            response.append("can't get ");
            response.append(objectToMove.getName());
            response.append(" from ");
            response.append(this.instance.getName());
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
        return "";
    }

}
