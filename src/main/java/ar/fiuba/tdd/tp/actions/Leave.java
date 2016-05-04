package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class Leave extends Move {

    public Leave(ConcreteGameObjectWithParentAndChildren instance) {
        super(instance, 1);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        return super.handleAction(getName(), objectsInvolved);
    }

    @Override
    protected String successMessage(List<GameObject> objectsInvolved) {
        GameObject objectToMove = objectsInvolved.get(idObjectToMove);
        return "left " + objectToMove.getName();
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        GameObject whereToMove = ((ConcreteGameObjectWithParentAndChildren)this.instance).getParent();
        objectsInvolved.add(whereToMove);
        return super.canIHandleAction(objectsInvolved, response);
    }

    @Override
    public String getName() {
        return "leave";
    }
}
