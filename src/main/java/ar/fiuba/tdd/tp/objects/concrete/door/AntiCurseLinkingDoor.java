package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.actions.BeOpenedFailsIfOpenerCursed;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;

public class AntiCurseLinkingDoor extends LinkingDoor {
    public AntiCurseLinkingDoor(String name, GameObjectWithChildren parent, GameObjectWithChildren nextRoom) {
        super(name, parent, nextRoom);

        addAction(new BeOpenedFailsIfOpenerCursed(this, open));
    }
}
