package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class Pick extends Move {

    public Pick(ConcreteGameObjectWithParentAndChildren instance) {
        super(instance, 1);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        return super.handleAction(getName(), objectsInvolved);
    }

    private void convertToMove(List<GameObject> objectsInvolved) {
        GameObject whereToMove = this.instance;
        objectsInvolved.add(whereToMove);
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        convertToMove(objectsInvolved);

        GameObject objectToPick = objectsInvolved.get(idObjectToMove);
        ConcreteGameObjectWithParentAndChildren picker = (ConcreteGameObjectWithParentAndChildren) this.instance;
        if (!picker.getParent().containsInHierarchy(objectToPick.getName())) {
            response.append(objectToPick.getName());
            response.append(" is not accessible");
            return false;
        }

        return super.canIHandleAction(objectsInvolved, response);
    }

    @Override
    protected String successMessage(List<GameObject> objectsInvolved) {
        GameObject objectToMove = objectsInvolved.get(idObjectToMove);
        return "picked " + objectToMove.getName();
    }

    @Override
    public String getName() {
        return "pick";
    }
}
