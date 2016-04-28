package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.actions.BeOpenedFailsIfOpenerCursed;
import ar.fiuba.tdd.tp.objects.general.GameObject;

/**
 * Created by Master on 28/04/2016.
 */
public class AntiCurseLinkingDoor extends LinkingDoor {
    public AntiCurseLinkingDoor(String name, GameObject nextRoom) {
        super(name, nextRoom);

        addAction(new BeOpenedFailsIfOpenerCursed(this, open));
    }
}
