package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.actions.BeOpenedMovesOpenerToOtherRoom;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;
import ar.fiuba.tdd.tp.objects.states.ParentState;

public class LinkingLockedDoor extends LockedDoor {

    protected ParentState nextRoom;

    public LinkingLockedDoor(String name, GameObjectWithChildren parent, int key, GameObjectWithChildren nextRoom) {
        super(name, parent, key);

        this.nextRoom = new ParentState();
        this.nextRoom.setParent(nextRoom);

        addAction(new BeOpenedMovesOpenerToOtherRoom(this, open, this.nextRoom));
    }
}
