package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.abilities.CanBeClosed;
import ar.fiuba.tdd.tp.abilities.CanCloseThings;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeClosed;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanCloseThings;

import java.util.List;

public class Close implements Action {
    @Override
    public String doAction(List<GameObject> objectsInvolved) {
        // ej: close door
        if (!(objectsInvolved.size() == 2)
                || !(objectsInvolved.get(0) instanceof GameObjectCanCloseThings)
                || !(objectsInvolved.get(1) instanceof GameObjectCanBeClosed)) {
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
