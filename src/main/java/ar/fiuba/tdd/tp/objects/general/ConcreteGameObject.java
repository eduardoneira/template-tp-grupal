package ar.fiuba.tdd.tp.objects.general;

import ar.fiuba.tdd.tp.actions.ActionHandler;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ConcreteGameObject implements GameObject {
    private final Map<String, List<ActionHandler>> actions;
    private final String name;

    public ConcreteGameObject(String name) {
        this.name = name;
        this.actions = new HashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    private String commandNotFoundResponse(String actionName) {
        return getName()
                + " has no command "
                + actionName;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        if (!actions.containsKey(actionName)) {
            return commandNotFoundResponse(actionName);
        }

        StringBuilder builder = new StringBuilder();
        actions.get(actionName).stream().filter(action -> action.canHandleAction(actionName, objectsInvolved, builder))
                .forEach(action -> builder.append(action.handleAction(actionName, objectsInvolved)));
        //return actions.get(actionName).handleAction(actionName, objectsInvolved);
        return builder.toString();
    }

    @Override
    public boolean canHandleAction(String actionName, List<GameObject> objectsInvolved, StringBuilder response) {
        boolean canHandle = true;
        if (actions.containsKey(actionName)) {
            for (ActionHandler action : actions.get(actionName)) {
                canHandle &= action.canHandleAction(actionName, objectsInvolved, response);
            }
            return canHandle;
        } else {
            response.append(commandNotFoundResponse(actionName));
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
            allActions.addAll(actions.get(actionName).stream().collect(Collectors.toList()));
        }
        return allActions;
    }

    @Override
    public List<String> getActionNames() {

        return getActions().stream().map(ActionHandler::getName)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
