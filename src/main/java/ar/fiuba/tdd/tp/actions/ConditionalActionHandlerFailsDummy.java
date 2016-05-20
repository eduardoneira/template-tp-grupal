package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.List;

/**
 * Created by Master on 20/05/2016.
 */
public class ConditionalActionHandlerFailsDummy extends ConditionalActionHandlerFails {
    public ConditionalActionHandlerFailsDummy(GameObject instance, ActionHandler action, List<BooleanState> conditions) {
        super(instance, action, conditions);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        StringBuilder response = new StringBuilder();
        if (!checkConditions(response)) {
            return response.toString();
        }

        return "";
    }
}
