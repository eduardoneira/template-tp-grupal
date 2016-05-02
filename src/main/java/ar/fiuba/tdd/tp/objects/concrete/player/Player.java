package ar.fiuba.tdd.tp.objects.concrete.player;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.general.*;

public class Player extends ConcreteGameObjectWithParentAndChildren {

    public Player(String name, GameObjectWithChildren parent) {
        super(name, parent);
        addAction(new Look(this));
        addAction(new BeMoved(this, this.parent));
        addAction(new HaveMovedTo(this, children));
        addAction(new What(this));
        addAction(new BeLookedAt(this));
    }

    public void placeInRoom(Room room) {
        this.setParent(room);
    }
}
