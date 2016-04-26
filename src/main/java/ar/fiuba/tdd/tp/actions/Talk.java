package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.GameObject;

import java.util.List;

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
