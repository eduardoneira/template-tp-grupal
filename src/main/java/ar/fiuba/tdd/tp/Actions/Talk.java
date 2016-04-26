package ar.fiuba.tdd.tp.Actions;

import ar.fiuba.tdd.tp.Objects.GameObject;

import java.util.List;

/**
 * Created by Master on 26/04/2016.
 */
public class Talk implements Action {
    @Override
    public String doAction(List<GameObject> objectsInvolved) {
        // ej: talk to thief
        if (!(objectsInvolved.size() == 2) || !(objectsInvolved.get(0) instanceof CanTalkToOthers) || !(objectsInvolved.get(1) instanceof CanBeTalkedTo)) {
            return "invalid command";
        }

        CanTalkToOthers objectThatTalks = (CanTalkToOthers) objectsInvolved.get(0);
        CanBeTalkedTo objectThatIsTalkedTo = (CanBeTalkedTo) objectsInvolved.get(1);
        return objectThatTalks.talkTo(objectThatIsTalkedTo);
    }

    @Override
    public String getName() {
        return "talk";
    }
}
