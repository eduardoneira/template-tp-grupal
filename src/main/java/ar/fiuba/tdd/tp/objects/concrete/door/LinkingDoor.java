package ar.fiuba.tdd.tp.objects.concrete.door;

import ar.fiuba.tdd.tp.actions.BeOpenedMovesOpenerToOtherRoom;
import ar.fiuba.tdd.tp.actions.Move;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;
import ar.fiuba.tdd.tp.objects.states.ParentState;

public class LinkingDoor extends Door {

    protected ParentState nextRoom;

    public LinkingDoor(String name, GameObjectWithChildren parent, GameObjectWithChildren nextRoom) {
        super(name, parent);

        this.nextRoom = new ParentState();
        this.nextRoom.setParent(nextRoom);

        addAction(new BeOpenedMovesOpenerToOtherRoom(this, open, this.nextRoom));
        addAction(new Move(this));
    }
}
