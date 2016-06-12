package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.List;

public class ConditionalActionHandlerChecks extends ConditionalActionHandlerFails {
    public ConditionalActionHandlerChecks(GameObject instance, ActionHandler action,
                                          List<BooleanState> conditions, List<Boolean> conditionsValues) {
        super(instance, action, conditions, conditionsValues);
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
