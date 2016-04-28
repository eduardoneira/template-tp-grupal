package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

/**
 * Created by fernando on 28/04/16.
 */
public class MoveTop extends Move {
    public MoveTop(ConcreteGameObjectWithParentAndChildren instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        return super.handleAction(actionName, objectsInvolved);
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        return super.canIHandleAction(objectsInvolved);
    }

    @Override
    public String getName() {
        return "Move Top";
    }

}
