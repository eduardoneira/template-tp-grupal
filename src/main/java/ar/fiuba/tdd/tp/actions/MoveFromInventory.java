package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.List;

/**
 * Created by Master on 19/05/2016.
 */
public class MoveFromInventory extends Move {

    public MoveFromInventory(ConcreteGameObjectWithParentAndChildren instance) {
        super(instance, 2);
    }

    public MoveFromInventory(ConcreteGameObjectWithParentAndChildren instance, int args) {
        super(instance, args);
    }

    @Override
    protected String successMessage(List<GameObject> objectsInvolved) {
        GameObject objectToMove = objectsInvolved.get(idObjectToMove);
        GameObject objectWhereToMove = objectsInvolved.get(idWhereToMove);
        return "put " + objectToMove.getName() + " on " + objectWhereToMove.getName();
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        GameObject objectToMove = objectsInvolved.get(idObjectToMove);
        ConcreteGameObjectWithParentAndChildren holder = (ConcreteGameObjectWithParentAndChildren) this.instance;
        if (!holder.contains(objectToMove.getName())) {
            response.append(objectToMove.getName());
            response.append(" is not accessible");
            return false;
        }

        GameObject objectWhereToMove = objectsInvolved.get(idWhereToMove);
        if (!holder.getParent().getName().equals(objectWhereToMove.getName()) && !holder.getParent().containsInHierarchy(objectWhereToMove.getName())) {
            response.append(objectWhereToMove.getName());
            response.append(" is not accessible");
            return false;
        }

        return super.canIHandleAction(objectsInvolved, response);
    }

    @Override
    public String getName() {
        return "put";
    }
}
