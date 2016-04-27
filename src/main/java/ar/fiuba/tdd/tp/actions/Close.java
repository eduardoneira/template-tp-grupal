package ar.fiuba.tdd.tp.actions;


import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeClosed;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanCloseThings;

import java.util.List;

public class Close implements Action {
    @Override
    public String doAction(List<GameObject> objectsInvolved) {
        // ej: close door
        if (BinaryActionChecker.check(objectsInvolved, GameObjectCanCloseThings.class, GameObjectCanBeClosed.class)) {
            return "invalid command";
        }

        GameObjectCanCloseThings objectThatDoesTheClosing = (GameObjectCanCloseThings) objectsInvolved.get(0);
        GameObjectCanBeClosed objectToClosed = (GameObjectCanBeClosed) objectsInvolved.get(1);

        return objectThatDoesTheClosing.close(objectToClosed);
    }

    @Override
    public String getName() {
        return "close";
    }
}
