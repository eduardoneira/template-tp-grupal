package ar.fiuba.tdd.tp.objects.general;

import ar.fiuba.tdd.tp.actions.ActionHandler;

import java.util.List;

public interface GameObject {

    String getName();

    String handleAction(String actionName, List<GameObject> objectsInvolved);

    boolean canHandleAction(String actionName, List<GameObject> objectsInvolved);

    void addAction(ActionHandler action);

    void removeAction(String actionName);
}
