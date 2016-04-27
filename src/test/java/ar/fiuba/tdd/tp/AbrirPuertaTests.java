package ar.fiuba.tdd.tp;


import ar.fiuba.tdd.tp.actions.Open;
import ar.fiuba.tdd.tp.objects.concrete.Key;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.door.Door;
import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import ar.fiuba.tdd.tp.objects.concrete.player.PlayerCanOpen;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AbrirPuertaTests {

    private Room room;
    private Player player;
    private Key key;
    private Door door;

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

        player = new PlayerCanOpen("player");
        player.addAction(new Open());
        player.placeInRoom(room);

        room.addChild(door);
        assert (door.isClosed());
        player.doAction("open",
                new ArrayList<String>() {
                    {
                        add("door");
                    }
                } );
        assert (door.isOpen());
    }

    /*@Test
    public void playerOpensLockedDoor() {
        door = new LockedDoor("door");
        door.setClosed();
        door.setLocked();
        door.setParent(room);

        player = new PlayerCanOpenLocks("player");
        player.addAction(new Open());
        player.placeInRoom(room);

        room.addChild(door);
        assert (door.isClosed());
        player.doAction("open", new ArrayList<String>(){{add("door");}});
        assert (door.isOpen());
    }*/
}
