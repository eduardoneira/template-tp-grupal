package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithParent;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.List;

public class HaveMovedTo extends ActionHandler {


    protected ChildrenState children;
    protected int idObjectToMove = 1;
    protected int argsSize = 2;

    public HaveMovedTo(GameObject instance, ChildrenState children) {
        super(instance);
        this.children = children;
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        if (!(objectsInvolved.size() == argsSize)) {
            setResponseError(objectsInvolved, response);
            return false;
        }
        if (!children.canAddChild()) {
            response.append(this.instance.getName());
            response.append(" is full");
            return false;
        }
        return true;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        GameObjectWithParent objectToMove = (GameObjectWithParent) objectsInvolved.get(idObjectToMove);
        children.addChild(objectToMove);
        return "done";
    }

    @Override
    public String getName() {
        return "have moved to";
    }
}
