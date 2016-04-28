package ar.fiuba.tdd.tp.objects.general;

import ar.fiuba.tdd.tp.actions.ActionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConcreteGameObject implements GameObject {
    private Map<String, ActionHandler> actions;
    private String name;

    public ConcreteGameObject(String name) {
        this.name = name;
        this.actions = new HashMap<String, ActionHandler>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        if(!actions.containsKey(actionName)) {
            return "";
        }
        return actions.get(actionName).handleAction(actionName, objectsInvolved);
    }

    @Override
    public boolean canHandleAction(String actionName, List<GameObject> objectsInvolved) {
        if (actions.containsKey(actionName)) {
            return actions.get(actionName).canHandleAction(actionName, objectsInvolved);
        }
        return false;
    }

    @Override
    public void addAction(ActionHandler action) {
        actions.put(action.getName(), action);
    }

    @Override
    public void removeAction(String actionName) {
        actions.remove(actionName);
    }
}
