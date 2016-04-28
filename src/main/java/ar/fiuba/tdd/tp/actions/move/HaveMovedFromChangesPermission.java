package ar.fiuba.tdd.tp.actions.move;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.List;

/**
 * Created by Master on 27/04/2016.
 */
public class HaveMovedFromChangesPermission extends HaveMovedFrom {

    protected BooleanState canTakeFrom;

    public HaveMovedFromChangesPermission(GameObject instance, ChildrenState children, BooleanState canTakeFrom) {
        super(instance, children);
        this.canTakeFrom = canTakeFrom;
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {

        return super.canIHandleAction(objectsInvolved) && canTakeFrom.getValue();
    }
}
