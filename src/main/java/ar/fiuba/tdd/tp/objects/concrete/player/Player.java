package ar.fiuba.tdd.tp.objects.concrete.player;

import ar.fiuba.tdd.tp.actions.BeMoved;
import ar.fiuba.tdd.tp.actions.HaveMovedTo;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.general.*;

public class Player extends ConcreteGameObjectWithParentAndChildren {

    public Player(String name) {
        super(name);
        addAction(new BeMoved(this, parent));
        addAction(new HaveMovedTo(this, children));
    }

    public void placeInRoom(Room room) {
        this.setParent(room);
    }
}
