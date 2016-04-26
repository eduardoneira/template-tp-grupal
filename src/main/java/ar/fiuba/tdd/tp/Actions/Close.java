package ar.fiuba.tdd.tp.Actions;

import ar.fiuba.tdd.tp.Objects.GameObject;

import java.util.List;

/**
 * Created by Master on 26/04/2016.
 */
public class Close implements Action {
    @Override
    public String doAction(List<GameObject> objectsInvolved) {
        // ej: close door
        if (!(objectsInvolved.size() == 2) || !(objectsInvolved.get(0) instanceof CanCloseThings) || !(objectsInvolved.get(1) instanceof CanBeClosed)) {
            return "invalid command";
        }

        CanCloseThings objectThatDoesTheClosing = (CanCloseThings) objectsInvolved.get(0);
        CanBeClosed objectToClosed = (CanBeClosed) objectsInvolved.get(1);

        return objectThatDoesTheClosing.close(objectToClosed);
    }

    @Override
    public String getName() {
        return "close";
    }
}
