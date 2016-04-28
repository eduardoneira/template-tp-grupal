package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.actions.BeOpenedMovesOpenerToOtherRoom;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ParentState;

/**
 * Created by Master on 28/04/2016.
 */
public class LinkingLockedDoor extends LockedDoor {

    protected ParentState nextRoom;

    public LinkingLockedDoor(String name, int key, GameObject nextRoom) {
        super(name, key);

        this.nextRoom = new ParentState();
        this.nextRoom.setParent(nextRoom);

        addAction(new BeOpenedMovesOpenerToOtherRoom(this, open, this.nextRoom));
    }
}
