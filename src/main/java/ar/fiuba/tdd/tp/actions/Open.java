package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.GameObject;

import java.util.List;

public class Open implements Action {
    @Override
    public String doAction(List<GameObject> objectsInvolved) {
        // ej: open door
        if (!(objectsInvolved.size() == 2) || !(objectsInvolved.get(0) instanceof CanOpen) || !(objectsInvolved.get(1) instanceof CanBeOpened)) {
            return "invalid command";
        }

        CanOpen objectThatDoesTheOpening = (CanOpen) objectsInvolved.get(0);
        CanBeOpened objectThatIsOpened = (CanBeOpened) objectsInvolved.get(1);

        return objectThatDoesTheOpening.open(objectThatIsOpened);
    }

    @Override
    public String getName() {
        return "open";
    }
}
