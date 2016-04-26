package ar.fiuba.tdd.tp.Actions;

import ar.fiuba.tdd.tp.Objects.GameObject;

import java.util.List;

/**
 * Created by Master on 26/04/2016.
 */
public class What implements Action {
    @Override
    public String doAction(List<GameObject> objectsInvolved) {
        // ej: what can i do with door
        if (!(objectsInvolved.size() == 2) || !(objectsInvolved.get(1) instanceof CanBeCheckedForPossibleActions)) {
            return "invalid command";
        }
        // tal vez deberia ser objectThatChecks.check(objectToCheck)
        CanBeCheckedForPossibleActions objectToCheck = (CanBeCheckedForPossibleActions) objectsInvolved.get(1);
        return objectToCheck.checkForPossibleActions();
    }

    @Override
    public String getName() {
        return "what";
    }
}
