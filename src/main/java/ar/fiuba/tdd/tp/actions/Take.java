package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithParent;

import java.util.List;

public class Take extends Pick {

    public Take(ConcreteGameObjectWithParentAndChildren instance) {
        super(instance);
    }

    @Override
    protected String successMessage(List<GameObject> objectsInvolved) {
        GameObject objectToMove = objectsInvolved.get(idObjectToMove);
        return "took " + objectToMove.getName();
    }

    @Override
    public String getName() {
        return "take";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        GameObjectWithParent objectToMove = (GameObjectWithParent) objectsInvolved.get(idObjectToMove);
        ConcreteGameObjectWithParentAndChildren taker = (ConcreteGameObjectWithParentAndChildren) this.instance;

        if (!taker.getParent().containsInHierarchy(objectToMove.getName())) {
            response.append(objectToMove.getName());
            response.append(" is not accessible");
            return false;
        }

        return super.canIHandleAction(objectsInvolved, response);
    }
}
