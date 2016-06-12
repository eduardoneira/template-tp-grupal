package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.List;

public class ConditionalActionHandlerFails extends ActionHandler {

    protected final List<BooleanState> conditions;
    protected final List<Boolean> conditionsValues;
    protected final ActionHandler action;

    public ConditionalActionHandlerFails(GameObject instance, ActionHandler action,
                                         List<BooleanState> conditions, List<Boolean> conditionsValues) {
        super(instance, action.argsSize);
        this.action = action;
        this.conditions = conditions;
        this.conditionsValues = conditionsValues;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        StringBuilder response = new StringBuilder();
        if (!checkConditions(objectsInvolved, response)) {
            return response.toString();
        }

        return action.handleAction(actionName, objectsInvolved);
    }

    protected void setInstance(GameObject instance) {
        action.setInstance(instance);
        this.instance = instance;
    }

    protected boolean checkConditions(List<GameObject> objectsInvolved, StringBuilder response) {
        for (int i = 0; i < conditions.size(); ++i) {
            if (conditions.get(i).getValue() != conditionsValues.get(i).booleanValue()) {
                response.append("a condition was not met in conditional command ");
                response.append(getName());
                return false;
            }
        }

        return true;
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        return checkConditions(objectsInvolved, response) && action.canIHandleAction(objectsInvolved, response);
    }

    @Override
    public String getName() {
        return action.getName();
    }
}
