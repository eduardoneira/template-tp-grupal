package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.actions.BeOpenedMovesOpenerToOtherRoom;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ParentState;

/**
 * Created by Master on 28/04/2016.
 */
public class LinkingDoor extends Door {

    protected ParentState nextRoom;

    public LinkingDoor(String name, GameObject nextRoom) {
        super(name);

        this.nextRoom = new ParentState();
        this.nextRoom.setParent(nextRoom);

        addAction(new BeOpenedMovesOpenerToOtherRoom(this, open, this.nextRoom));
    }
}
