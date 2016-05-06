package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.concrete.CursedKey;
import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Thief;
import ar.fiuba.tdd.tp.objects.concrete.door.AntiCurseLinkingDoor;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingLockedDoor;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class CursedObjectTests {

    private Room room1;
    private LinkingLockedDoor door1to2;
    private CursedKey keyObject;
    int keyNumber = 2;

    private Room room2;
    private AntiCurseLinkingDoor door2to3;

    private Room room3;

    private Player player;
    private Thief thief;
    private List<GameObject> objectsInvolved;

    @Before
    public void initialization() {
        room1 = new Room("room1");
        room2 = new Room("room2");
        room3 = new Room("room3");

        door1to2 = new LinkingLockedDoor("door1to2", room1, keyNumber, room2, new BooleanState());
        door1to2.setClosed();
        door1to2.setLocked();

        door2to3 = new AntiCurseLinkingDoor("door2to3", room2, room3);
        door2to3.setClosed();

        addActionsAndThief();

        keyObject = new CursedKey("key", room1, keyNumber);

        objectsInvolved = new LinkedList<>();
    }

    public void addActionsAndThief() {
        player = new Player("player", room1);
        player.addAction(new Open(player));
        player.addAction(new Move(player));
        player.addAction(new Pick(player));
        player.addAction(new Talk(player));
        player.addAction(new HaveEverythingStolen(player, player.getChildrenState()));
        player.addAction(new HaveMovedFrom(player, player.getChildrenState()));

        thief = new Thief("thief", room2);

    }

    @Test
    public void cantGoToRoom2WithoutKey() {
        assert (player.getParent().equals(room1));
        objectsInvolved.add(door1to2);
        player.handleAction("open", objectsInvolved);
        assert (player.getParent().equals(room1));
        objectsInvolved.clear();
    }

    @Test
    public void canGoToRoom2WithKey() {

        assert (!player.contains("key"));
        assert (room1.contains("key"));
        objectsInvolved.add(keyObject);
        player.handleAction("pick", objectsInvolved);
        assert (player.contains("key"));
        assert (!room1.contains("key"));
        objectsInvolved.clear();

        assert (player.getParent().equals(room1));
        objectsInvolved.add(door1to2);
        player.handleAction("open", objectsInvolved);
        assert (player.getParent().equals(room2));
        objectsInvolved.clear();
    }

    @Test
    public void cantGoToRoom3WithKey() {
        canGoToRoom2WithKey();

        assert (player.getParent().equals(room2));
        objectsInvolved.add(door2to3);
        player.handleAction("open", objectsInvolved);
        assert (player.getParent().equals(room2));
        objectsInvolved.clear();
    }

    @Test
    public void thiefStealsItems() {
        canGoToRoom2WithKey();

        assert (player.contains("key"));
        objectsInvolved.add(thief);
        player.handleAction("talk", objectsInvolved);
        assert (!player.contains("key"));
        objectsInvolved.clear();
    }

    @Test
    public void canGoToRoom3AfterThiefSteals() {
        thiefStealsItems();

        assert (player.getParent().equals(room2));
        objectsInvolved.add(door2to3);
        player.handleAction("open", objectsInvolved);
        assert (player.getParent().equals(room3));
        objectsInvolved.clear();
    }
}
