package ar.fiuba.tdd.tp.objects.general;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.actions.BeAskedWhat;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ConcreteGameObject implements GameObject {
    private Map<String, List<ActionHandler>> actions;
    private String name;

    public ConcreteGameObject(String name) {
        this.name = name;
        this.actions = new HashMap<String, List<ActionHandler>>();

        addAction(new BeAskedWhat(this));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        if (!actions.containsKey(actionName)) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (ActionHandler action : actions.get(actionName)) {
            builder.append(action.handleAction(actionName, objectsInvolved));
        }
        //return actions.get(actionName).handleAction(actionName, objectsInvolved);
        return builder.toString();
    }

    @Override
    public boolean canHandleAction(String actionName, List<GameObject> objectsInvolved) {
        boolean canHandle = true;
        if (actions.containsKey(actionName)) {
            for (ActionHandler action : actions.get(actionName)) {
                canHandle &= action.canHandleAction(actionName, objectsInvolved);
            }
            return canHandle;
        } else {
            return false;
        }
    }

    @Override
    public void addAction(ActionHandler action) {
        if (actions.containsKey(action.getName())) {
            actions.get(action.getName()).add(action);
        } else {
            List<ActionHandler> newActions = new LinkedList<>();
            newActions.add(action);
            actions.put(action.getName(), newActions);
        }
    }

    @Override
    public void removeAction(String actionName) {
        actions.remove(actionName);
    }

    @Override
    public List<ActionHandler> getActions() {
        List<ActionHandler> allActions = new LinkedList<>();
        for (String actionName : actions.keySet()) {
            for (ActionHandler action : actions.get(actionName)) {
                allActions.add(action);
            }
        }
        return allActions;
    }

    @Override
    public List<String> getActionNames() {
        List<String> allActions = new LinkedList<>();

        for ( ActionHandler action : getActions()) {
            allActions.add(action.getName());
        }

        return allActions;
    }
}
