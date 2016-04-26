package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.GameObject;

import java.util.List;

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
