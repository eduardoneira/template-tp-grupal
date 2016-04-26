package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.abilities.CanBeLookedAt;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeLookedAt;

import java.util.List;

public class LookAt implements Action {

    @Override
    public String doAction(List<GameObject> objectsInvolved) {
        // ej: look at room
        // por ahora asumo que cualquiera puede 'ver' cosas, por eso no chequeo al obj[0]
        if (!(objectsInvolved.size() == 2) || !(objectsInvolved.get(1) instanceof GameObjectCanBeLookedAt)) {
            return "invalid command";
        }
        // tal vez deberia ser objectThatLooks.lookAt(objectThatIsLookedAt)
        GameObjectCanBeLookedAt objectThatIsLookedAt = (GameObjectCanBeLookedAt) objectsInvolved.get(1);
        return objectThatIsLookedAt.lookAt();
    }

    @Override
    public String getName() {
        return "look";
    }
}
