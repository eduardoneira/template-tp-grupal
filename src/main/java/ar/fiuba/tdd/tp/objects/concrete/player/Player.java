package ar.fiuba.tdd.tp.objects.concrete.player;

import ar.fiuba.tdd.tp.actions.BeMoved;
import ar.fiuba.tdd.tp.actions.HaveMovedTo;
import ar.fiuba.tdd.tp.actions.Look;
import ar.fiuba.tdd.tp.actions.What;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.general.*;

public class Player extends ConcreteGameObjectWithParentAndChildren {

    public Player(String name) {
        super(name);
        addAction(new Look(this));
        addAction(new BeMoved(this, parent));
        addAction(new HaveMovedTo(this, children));
        addAction(new What(this));
    }

    public void placeInRoom(Room room) {
        this.setParent(room);
    }
}
