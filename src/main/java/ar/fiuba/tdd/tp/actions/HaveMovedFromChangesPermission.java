package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.List;

public class HaveMovedFromChangesPermission extends HaveMovedFrom {

    protected BooleanState canTakeFrom;

    public HaveMovedFromChangesPermission(GameObject instance, ChildrenState children, BooleanState canTakeFrom) {
        super(instance, children);
        this.canTakeFrom = canTakeFrom;
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {

        if (!super.canIHandleAction(objectsInvolved, response)) {
            return false;
        }

        if (!canTakeFrom.getValue()) {
            response.append(objectsInvolved.get(idObjectToMove).getName());
            response.append(" can't be accesed");
            return false;
        }

        return true;
    }
}
