package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.concrete.CursedKey;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Thief;
import ar.fiuba.tdd.tp.objects.concrete.door.AntiCurseLinkingDoor;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingLockedDoor;
import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import ar.fiuba.tdd.tp.objects.concrete.player.PlayerCursedObject;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Master on 28/04/2016.
 */
public class CursedObject extends Game {

    private Room room1;
    private LinkingLockedDoor door1to2;
    private CursedKey keyObject;
    int keyNumber = 2;

    private Room room2;
    private AntiCurseLinkingDoor door2to3;

    private Room room3;

    private Player player;
    private Thief thief;
    //private List<GameObject> objectsInvolved;

    private void initialization() {
        room1 = new Room("room1");
        room2 = new Room("room2");
        room3 = new Room("room3");

        door1to2 = new LinkingLockedDoor("door1to2", room1, keyNumber, room2);
        door1to2.setClosed();
        door1to2.setLocked();
        room1.addChild(door1to2);

        door2to3 = new AntiCurseLinkingDoor("door2to3", room2, room3);
        door2to3.setClosed();
        room2.addChild(door2to3);

        player = new PlayerCursedObject("player", room1);
        setUpPlayers();
        keyObject = new CursedKey("key", room1, keyNumber);
        room1.addChild(keyObject);

        //objectsInvolved = new LinkedList<>();

        setUpObjectsAndKeywords();
    }

    public void setUpPlayers() {
        player.addAction(new Open(player));
        player.addAction(new Move(player));
        player.addAction(new Look(player));
        player.addAction(new What(player));
        player.addAction(new Pick(player));
        player.addAction(new Talk(player));
        room1.addChild(player);

        thief = new Thief("thief", room2);
        room2.addChild(thief);
    }

    public void setUpObjectsAndKeywords() {


        keywords.add(door1to2.getName());

        keywords.add(door2to3.getName());
        keywords.add(room1.getName());
        keywords.add(room2.getName());

        keywords.add(thief.getName());
        keywords.add(room3.getName());

        keywords.add(keyObject.getName());


        objects.put(room1.getName(), room1);
        objects.put(door1to2.getName(), door1to2);

        objects.put(room2.getName(), room2);
        objects.put(thief.getName(), thief);
        addActionToKeywords();
        objects.put(room3.getName(), room3);
        objects.put(door2to3.getName(), door2to3);
        objects.put(keyObject.getName(), keyObject);
    }

    public CursedObject() {
        super("Cursed Object");
        initialization();
    }

    @Override
    public boolean checkWinCondition() {
        return room3.contains("player");
    }
}
