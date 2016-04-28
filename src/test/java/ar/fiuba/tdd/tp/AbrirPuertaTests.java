package ar.fiuba.tdd.tp;


import ar.fiuba.tdd.tp.actions.open.Open;
import ar.fiuba.tdd.tp.actions.open.Unlock;
import ar.fiuba.tdd.tp.objects.concrete.Key;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.door.Door;
import ar.fiuba.tdd.tp.objects.concrete.door.LockedDoor;
import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AbrirPuertaTests {

    private Room room;
    private Player player;
    private Key key;
    private Door door;
    private LockedDoor lockedDoor;

    @Before
    public void initialization() {
        room = new Room("room");

        key = new Key("key");
        key.setParent(room);

        room.addChild(key);
    }

    @Test
    public void placeDoorInRoom() {
        door = new Door("door");
        door.setClosed();
        door.setParent(room);
        room.addChild(door);

        assert (room.contains("door"));
    }

    @Test
    public void playerOpensDoor() {
        door = new Door("door");
        door.setClosed();
        door.setParent(room);
        room.addChild(door);

        player = new Player("player");
        player.addAction(new Open(player));
        player.placeInRoom(room);

        assert (door.isClosed());
        player.handleAction("open",
                new ArrayList<GameObject>() {
                    {
                        add(door);
                    }
                } );
        assert (door.isOpen());
    }

    @Test
    public void playerOpensLockedDoor() {
        int key = 2;

        lockedDoor = new LockedDoor("door", key);
        lockedDoor.setClosed();
        lockedDoor.setLocked();
        lockedDoor.setParent(room);
        room.addChild(lockedDoor);

        player = new Player("player");
        player.addAction(new Open(player));
        player.placeInRoom(room);

        assert (lockedDoor.isClosed());
        player.handleAction("open", new ArrayList<GameObject>(){{add(lockedDoor);}});
        assert (lockedDoor.isClosed());
        player.addAction(new Unlock(player, key));
        player.handleAction("open", new ArrayList<GameObject>(){{add(lockedDoor);}});
        assert (lockedDoor.isOpen());
    }
}
