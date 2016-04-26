package ar.fiuba.tdd.tp.Actions;

import ar.fiuba.tdd.tp.Objects.GameObject;

import java.util.List;

/**
 * Created by Master on 26/04/2016.
 */
public class Open implements Action {
    @Override
    public String doAction(List<GameObject> objectsInvolved) {
        // ej: open door
        if (!(objectsInvolved.size() == 2) || !(objectsInvolved.get(0) instanceof CanOpenThings) || !(objectsInvolved.get(1) instanceof CanBeOpened)) {
            return "invalid command";
        }

        CanOpenThings objectThatDoesTheOpening = (CanOpenThings) objectsInvolved.get(0);
        CanBeOpened objectThatIsOpened = (CanBeOpened) objectsInvolved.get(1);

        return objectThatDoesTheOpening.open(objectThatIsOpened);
    }

    @Override
    public String getName() {
        return "open";
    }
}
