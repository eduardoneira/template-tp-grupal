package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.List;

/**
 * Created by Master on 03/05/2016.
 */
public class ConditionalActionHandlerFails extends ActionHandler {

    protected List<BooleanState> conditions;
    protected ActionHandler action;

    public ConditionalActionHandlerFails(GameObject instance, ActionHandler action, List<BooleanState> conditions) {
        super(instance);
        this.action = action;
        this.conditions = conditions;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        StringBuilder response = new StringBuilder();
        if (!checkConditions(response)) {
            return response.toString();
        }

        return action.handleAction(actionName, objectsInvolved);
    }

    protected boolean checkConditions(StringBuilder response) {
        for (BooleanState condition : conditions) {
            if (!condition.isTrue()) {
                response.append("a condition was not met in conditional command ");
                response.append(getName());
                return false;
            }
        }

        return true;
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        if (!checkConditions(response)) {
            return false;
        }
        return action.canIHandleAction(objectsInvolved, response);
    }

    @Override
    public String getName() {
        return action.getName();
    }
}
