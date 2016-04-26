package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.abilities.CanBeMoved;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeMoved;

import java.util.List;

public class Pick extends Move {
    @Override
    public String doAction(List<GameObject> objectsInvolved) {
        if (objectsInvolved.size() == 0) {
            return "No object selected";
        }
        // esto se lo fija move ya, deberia ir ahi de ultima
        if (!(objectsInvolved.get(1) instanceof GameObjectCanBeMoved)) {
            return "Cannot be moved";
        }
        // ej: pick stick -> move stick player
        objectsInvolved.add(objectsInvolved.get(0));
        return super.doAction(objectsInvolved);
    }

    @Override
    public String getName() {
        return "pick";
    }
}
