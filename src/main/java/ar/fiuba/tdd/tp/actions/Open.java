package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeOpened;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanOpen;

import java.util.List;

public class Open implements Action {
    @Override
    public String doAction(List<GameObject> objectsInvolved) {
        // ej: open door
        if (BinaryActionChecker.check(objectsInvolved, GameObjectCanOpen.class, GameObjectCanBeOpened.class)) {
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
