package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.abilities.CanBeTalkedTo;
import ar.fiuba.tdd.tp.abilities.CanTalkToOthers;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanBeTalkedTo;
import ar.fiuba.tdd.tp.objects.general.GameObjectCanTalkToOthers;

import java.util.List;

public class Talk implements Action {
    @Override
    public String doAction(List<GameObject> objectsInvolved) {
        // ej: talk to thief
        if (!(objectsInvolved.size() == 2) || !(objectsInvolved.get(0) instanceof GameObjectCanTalkToOthers) || !(objectsInvolved.get(1) instanceof GameObjectCanBeTalkedTo)) {
            return "invalid command";
        }

        GameObjectCanTalkToOthers objectThatTalks = (GameObjectCanTalkToOthers) objectsInvolved.get(0);
        GameObjectCanBeTalkedTo objectThatIsTalkedTo = (GameObjectCanBeTalkedTo) objectsInvolved.get(1);
        return objectThatTalks.talkTo(objectThatIsTalkedTo);
    }

    @Override
    public String getName() {
        return "talk";
    }
}
