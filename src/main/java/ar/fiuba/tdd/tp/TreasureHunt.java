package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.concrete.*;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingDoor;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingLockedDoor;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import ar.fiuba.tdd.tp.objects.states.ChildrenStateLimitedSize;

/**
 * Created by Master on 03/05/2016.
 */
public class TreasureHunt extends Game {

    @SuppressWarnings("CPD-START")

    private Room room1;
    private LinkingDoor door1to2;
    private Key keyTo3;

    private Room room2;
    private LinkingDoor door2to1;
    private LinkingLockedDoor door2to3;
    private LinkingLockedDoor door2to4;
    private LinkingLockedDoor door2to5;
    private Chest chestIn2;
    private Box boxInChestIn2;
    private Antidote antidoteIn2;

    private Room room3;
    private LinkingLockedDoor door3to2;
    private Box boxIn3;
    private Key keyTo5;

    private Room room4;
    private LinkingLockedDoor door4to2;
    private Chest chest1In4;
    private Poison poisonIn4;
    private Chest chest2In4;
    private Treasure treasureInChest2In4;

    private Room room5;
    private Antidote antidoteIn5;
    private LinkingLockedDoor door5to2;
    private Chest chest1In5;
    private Key keyTo4;
    private Chest chest2In5;
    private Poison poisonIn5;

    private BooleanState killedByPoison;
    private BooleanState poisoned;

    public TreasureHunt() {
        super("Treasure Hunt");

        createRooms();

        initBooleans();

        populateRoom1();

        populateRoom2();

        populateRoom3();

        populateRoom4();

        populateRoom5();

        createPlayer();
    }

    private void createRooms() {
        room1 = new Room("room1");
        keywords.add(room1.getName());
        objects.put(room1.getName(), room1);

        room2 = new Room("room2");
        keywords.add(room2.getName());
        objects.put(room2.getName(), room2);

        room3 = new Room("room3");
        keywords.add(room3.getName());
        objects.put(room3.getName(), room3);

        room4 = new Room("room4");
        keywords.add(room4.getName());
        objects.put(room4.getName(), room4);

        room5 = new Room("room5");
        keywords.add(room5.getName());
        objects.put(room5.getName(), room5);
    }

    private void initBooleans() {
        killedByPoison = new BooleanState();
        killedByPoison.setFalse();
        poisoned = new BooleanState();
        poisoned.setFalse();
    }

    private void populateRoom1() {
        // room1
        door1to2 = new LinkingDoor("door1to2", room1, room2);
        room1.addChild(door1to2);
        keywords.add(door1to2.getName());
        objects.put(door1to2.getName(), door1to2);

        keyTo3 = new Key("keyTo3", room1, 3);
        room1.addChild(keyTo3);
        keywords.add(keyTo3.getName());
        objects.put(keyTo3.getName(), keyTo3);
    }

    private void populateRoom2() {
        // room2
        populateRoom2a();

        populateRoom2b();
    }

    private void populateRoom2a() {
        door2to1 = new LinkingDoor("door2to1", room2, room1);
        room2.addChild(door2to1);
        keywords.add(door2to1.getName());
        objects.put(door2to1.getName(), door2to1);

        door2to3 = new LinkingLockedDoor("door2to3", room2, 3, room3);
        room2.addChild(door2to3);
        keywords.add(door2to3.getName());
        objects.put(door2to3.getName(), door2to3);

        door2to4 = new LinkingLockedDoor("door2to4", room2, 4, room4);
        room2.addChild(door2to4);
        keywords.add(door2to4.getName());
        objects.put(door2to4.getName(), door2to4);
    }

    private void populateRoom2b() {
        door2to5 = new LinkingLockedDoor("door2to5", room2, 5, room5);
        room2.addChild(door2to5);
        keywords.add(door2to5.getName());
        objects.put(door2to5.getName(), door2to5);

        chestIn2 = new Chest("chestIn2", room2);
        room2.addChild(chestIn2);
        keywords.add(chestIn2.getName());
        objects.put(chestIn2.getName(), chestIn2);

        boxInChestIn2 = new Box("boxIn2", chestIn2);
        chestIn2.addChild(boxInChestIn2);
        keywords.add(boxInChestIn2.getName());
        objects.put(boxInChestIn2.getName(), boxInChestIn2);

        antidoteIn2 = new Antidote("antidoteIn2", boxInChestIn2, poisoned);
        boxInChestIn2.addChild(antidoteIn2);
        keywords.add(antidoteIn2.getName());
        objects.put(antidoteIn2.getName(), antidoteIn2);
    }

    private void populateRoom3() {
        // room3

        door3to2 = new LinkingLockedDoor("door3to2", room3, 3, room2);
        room3.addChild(door3to2);
        keywords.add(door3to2.getName());
        objects.put(door3to2.getName(), door3to2);

        boxIn3 = new Box("boxIn3", room3);
        room3.addChild(boxIn3);
        keywords.add(boxIn3.getName());
        objects.put(boxIn3.getName(), boxIn3);

        keyTo5 = new Key("keyTo5", boxIn3, 5);
        boxIn3.addChild(keyTo5);
        keywords.add(keyTo5.getName());
        objects.put(keyTo5.getName(), keyTo5);
    }

    private void populateRoom4() {
        populateRoom4a();

        populateRoom4b();
    }

    private void populateRoom4a() {
        // room4

        door4to2 = new LinkingLockedDoor("door4to2", room4, 4, room2);
        room4.addChild(door4to2);
        keywords.add(door4to2.getName());
        objects.put(door4to2.getName(), door4to2);

        chest1In4 = new Chest("chest1In4", room4);
        room4.addChild(chest1In4);
        keywords.add(chest1In4.getName());
        objects.put(chest1In4.getName(), chest1In4);

        poisonIn4 = new Poison("poisonIn4", chest1In4, killedByPoison, poisoned);
        chest1In4.addChild(poisonIn4);
        keywords.add(poisonIn4.getName());
        objects.put(poisonIn4.getName(), poisonIn4);
    }

    private void populateRoom4b() {
        chest2In4 = new Chest("chest2In4", room4);
        room4.addChild(chest2In4);
        keywords.add(chest2In4.getName());
        objects.put(chest2In4.getName(), chest2In4);

        treasureInChest2In4  = new Treasure("treasure", chest2In4);
        chest2In4.addChild(treasureInChest2In4);
        keywords.add(treasureInChest2In4.getName());
        objects.put(treasureInChest2In4.getName(), treasureInChest2In4);
    }

    private void populateRoom5() {
        populateRoom5a();

        populateRoom5b();
    }

    private void populateRoom5a() {
        // room 5

        door5to2 = new LinkingLockedDoor("door5to2", room5, 5, room2);
        room5.addChild(door5to2);
        keywords.add(door5to2.getName());
        objects.put(door5to2.getName(), door5to2);

        antidoteIn5 = new Antidote("antidoteIn5", room5, poisoned);
        room5.addChild(antidoteIn5);
        keywords.add(antidoteIn5.getName());
        objects.put(antidoteIn5.getName(), antidoteIn5);

        chest1In5 = new Chest("chest1In5", room5);
        room5.addChild(chest1In5);
        keywords.add(chest1In5.getName());
        objects.put(chest1In5.getName(), chest1In5);
    }

    private void populateRoom5b() {
        keyTo4 = new Key("keyTo4", chest1In5, 4);
        chest1In5.addChild(keyTo4);
        keywords.add(keyTo4.getName());
        objects.put(keyTo4.getName(), keyTo4);

        chest2In5 = new Chest("chest2In5", room5);
        room5.addChild(chest2In5);
        keywords.add(chest2In5.getName());
        objects.put(chest2In5.getName(), chest2In5);

        poisonIn5 = new Poison("poisonIn5", chest2In5, killedByPoison, poisoned);
        chest2In5.addChild(poisonIn5);
        keywords.add(poisonIn5.getName());
        objects.put(poisonIn5.getName(), poisonIn5);
    }

    private void createPlayer() {
        player = new Player("player", room1, new ChildrenStateLimitedSize(2));
        room1.addChild(player);
        keywords.add(player.getName());
        objects.put(player.getName(), player);

        ActionHandler pickAction = new Pick(player);
        player.addAction(pickAction);
        keywords.add(pickAction.getName());

        ActionHandler openAction = new Open(player);
        player.addAction(openAction);
        keywords.add(openAction.getName());

        player.addAction(new HaveMovedFrom(player, player.getChildrenState()));
        ActionHandler leaveAction = new Leave(player);
        player.addAction(leaveAction);
        keywords.add(leaveAction.getName());
    }

    @SuppressWarnings("CPD-END")

    @Override
    public boolean checkWinCondition() {
        return player.contains(treasureInChest2In4.getName()) && room1.contains(player.getName());
    }

    @Override
    public boolean checkLooseCondition() {
        return killedByPoison.isTrue();
    }
}
