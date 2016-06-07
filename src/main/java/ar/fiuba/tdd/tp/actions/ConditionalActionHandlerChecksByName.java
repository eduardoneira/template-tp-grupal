package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.List;

/**
 * Created by Master on 07/06/2016.
 */
public class ConditionalActionHandlerChecksByName extends ConditionalActionHandlerFailsByName {

    public ConditionalActionHandlerChecksByName(GameObject instance, ActionHandler action, List<BooleanState> conditions, List<String> names, int i) {
        super(instance, action, conditions, names, i);
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        return action.canIHandleAction(objectsInvolved, response);
    }

    @Override
    protected boolean checkConditions(List<GameObject> objectsInvolved, StringBuilder response) {
        return super.checkConditions(objectsInvolved, new StringBuilder());
    }
}
