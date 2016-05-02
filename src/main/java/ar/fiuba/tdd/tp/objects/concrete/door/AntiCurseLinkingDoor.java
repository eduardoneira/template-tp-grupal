package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.actions.BeOpenedFailsIfOpenerCursed;
import ar.fiuba.tdd.tp.objects.general.GameObject;

public class AntiCurseLinkingDoor extends LinkingDoor {
    public AntiCurseLinkingDoor(String name, GameObject parent, GameObject nextRoom) {
        super(name, parent, nextRoom);

        addAction(new BeOpenedFailsIfOpenerCursed(this, open));
    }
}
