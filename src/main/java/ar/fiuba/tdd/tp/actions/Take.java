package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class Take extends Move {

    public Take(ConcreteGameObjectWithParentAndChildren instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        convertToMove(objectsInvolved);
        StringBuilder response = new StringBuilder();
        if (!canHandleAction(actionName, objectsInvolved, response)) {
            return response.toString();
        }
        super.handleAction(getName(), objectsInvolved);
        GameObject objectToMove = objectsInvolved.get(idObjectToMove);
        return "took " + objectToMove.getName();
    }

    private void convertToMove(List<GameObject> objectsInvolved) {
        GameObject whereToMove = this.instance;
        objectsInvolved.add(whereToMove);
    }

    @Override
    public String getName() {
        return "take";
    }
}
