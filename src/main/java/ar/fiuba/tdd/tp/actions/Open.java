package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.abilities.CanBeOpened;
import ar.fiuba.tdd.tp.abilities.CanOpen;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeOpened;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanOpen;

import java.util.List;

public class Open implements Action {
    @Override
    public String doAction(List<GameObject> objectsInvolved) {
        // ej: open door
        if (!(objectsInvolved.size() == 2)
                || !(objectsInvolved.get(0) instanceof GameObjectCanOpen)
                || !(objectsInvolved.get(1) instanceof GameObjectCanBeOpened)) {
            return "invalid command";
        }

        GameObjectCanOpen objectThatDoesTheOpening = (GameObjectCanOpen) objectsInvolved.get(0);
        GameObjectCanBeOpened objectThatIsOpened = (GameObjectCanBeOpened) objectsInvolved.get(1);

        return objectThatDoesTheOpening.open(objectThatIsOpened);
    }

    @Override
    public String getName() {
        return "open";
    }
}
