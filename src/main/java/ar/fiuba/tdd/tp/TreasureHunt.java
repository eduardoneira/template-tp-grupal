package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.model.*;
import ar.fiuba.tdd.tp.objects.concrete.*;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingDoor;
import ar.fiuba.tdd.tp.objects.concrete.door.LinkingLockedDoor;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithParent;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import ar.fiuba.tdd.tp.objects.states.ChildrenStateLimitedSize;

import java.util.*;


public class TreasureHunt extends Game {

    @SuppressWarnings("CPD-START")

    private BooleanState door2to3state;
    private BooleanState door2to4state;
    private BooleanState door2to5state;

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
    private GeneralMovableObject antidoteIn2;

    private Room room3;
    private LinkingLockedDoor door3to2;
    private Box boxIn3;
    private Key keyTo5;

    private Room room4;
    private LinkingLockedDoor door4to2;
    private Chest chest1In4;
    private GeneralMovableObject poisonIn4;
    private Chest chest2In4;
    private Treasure treasureInChest2In4;

    private Room room5;
    private GeneralMovableObject antidoteIn5;
    private LinkingLockedDoor door5to2;
    private Chest chest1In5;
    private Key keyTo4;
    private Chest chest2In5;
    private GeneralMovableObject poisonIn5;

    private List<BooleanState> killedByPoison;
    private List<Boolean> killedByPoisonTriggeredValues;
    private List<BooleanState> poisoned;
    private List<Boolean> poisonedTriggeredValues;
    private List<Boolean> antidoteTriggeredValues;
    private List<String> playerNames;

    public TreasureHunt() {
        super("TreasureHunt");
    }

    private void createRooms() {
        room1 = new Room("room1");
        objects.put(room1.getName(), room1);

        room2 = new Room("room2");
        objects.put(room2.getName(), room2);

        room3 = new Room("room3");
        objects.put(room3.getName(), room3);

        room4 = new Room("room4");
        objects.put(room4.getName(), room4);

        room5 = new Room("room5");
        objects.put(room5.getName(), room5);
    }

    private void initBooleans() {
        killedByPoison = new ArrayList<>();
        killedByPoisonTriggeredValues = new ArrayList<>();
        //killedByPoison.setFalse();
        poisoned = new ArrayList<>();
        poisonedTriggeredValues = new ArrayList<>();
        antidoteTriggeredValues = new ArrayList<>();
        //poisoned.setFalse();
        playerNames = new ArrayList<>();

        door2to3state = new BooleanState();
        door2to3state.setTrue();
        door2to4state = new BooleanState();
        door2to4state.setTrue();
        door2to5state = new BooleanState();
        door2to5state.setTrue();
    }

    private void populateRoom1() {
        // room1
        door1to2 = new LinkingDoor("door1to2", room1, room2);
        objects.put(door1to2.getName(), door1to2);

        keyTo3 = new Key("keyTo3", room1, 3);
        objects.put(keyTo3.getName(), keyTo3);
    }

    private void populateRoom2() {
        // room2
        populateRoom2a();

        populateRoom2b();
    }

    private void populateRoom2a() {
        door2to1 = new LinkingDoor("door2to1", room2, room1);
        objects.put(door2to1.getName(), door2to1);

        door2to3 = new LinkingLockedDoor("door2to3", room2, 3, room3, door2to3state);
        objects.put(door2to3.getName(), door2to3);

        door2to4 = new LinkingLockedDoor("door2to4", room2, 4, room4, door2to4state);
        objects.put(door2to4.getName(), door2to4);
    }

    private void populateRoom2b() {
        door2to5 = new LinkingLockedDoor("door2to5", room2, 5, room5, door2to5state);
        objects.put(door2to5.getName(), door2to5);

        chestIn2 = new Chest("chestIn2", room2);
        objects.put(chestIn2.getName(), chestIn2);

        boxInChestIn2 = new Box("boxIn2", chestIn2);
        objects.put(boxInChestIn2.getName(), boxInChestIn2);

        //antidoteIn2 = new Antidote("antidoteIn2", boxInChestIn2, poisoned);
        antidoteIn2 = new GeneralMovableObject("antidoteIn2", boxInChestIn2);
        BeUsed usedAction = new BeUsed(antidoteIn2, antidoteIn2.getParentState(), 1);
        antidoteIn2.addAction(new TriggerActionHandlerByName(
                antidoteIn2, usedAction, poisoned, playerNames, antidoteTriggeredValues, 0, "All your ailments are healed!"));
        antidoteIn2.addAction(usedAction);
        objects.put(antidoteIn2.getName(), antidoteIn2);
    }

    private void populateRoom3() {
        // room3

        door3to2 = new LinkingLockedDoor("door3to2", room3, 3, room2, door2to3state);
        objects.put(door3to2.getName(), door3to2);

        boxIn3 = new Box("boxIn3", room3);
        objects.put(boxIn3.getName(), boxIn3);

        keyTo5 = new Key("keyTo5", boxIn3, 5);
        objects.put(keyTo5.getName(), keyTo5);
    }

    private void populateRoom4() {
        populateRoom4a();

        populateRoom4b();
    }

    private void populateRoom4a() {
        // room4

        door4to2 = new LinkingLockedDoor("door4to2", room4, 4, room2, door2to4state);
        objects.put(door4to2.getName(), door4to2);

        chest1In4 = new Chest("chest1In4", room4);
        objects.put(chest1In4.getName(), chest1In4);

        //poisonIn4 = new Poison("poisonIn4", chest1In4, killedByPoison, poisoned);

        poisonIn4 = new GeneralMovableObject("poisonIn4", chest1In4);
        chest1In4.addAction(new TriggerActionHandlerByName(
                chest1In4, new BeOpened(null, null), poisoned, playerNames, poisonedTriggeredValues, 0));
        List<ActionHandler> actions = new LinkedList<>();
        actions.add(new ConditionalActionHandlerChecksByName(poisonIn4,
                new TriggerActionHandlerByName(poisonIn4,
                        new BeMoved(null, null), killedByPoison, playerNames, killedByPoisonTriggeredValues, -1),
                poisoned, poisonedTriggeredValues, playerNames, -1));
        chest1In4.addAction(new BeOpenedAddsActionsToOpener(chest1In4, actions, "You feel weak!"));
        objects.put(poisonIn4.getName(), poisonIn4);
    }

    private void populateRoom4b() {
        chest2In4 = new Chest("chest2In4", room4);
        objects.put(chest2In4.getName(), chest2In4);

        treasureInChest2In4  = new Treasure("treasure", chest2In4);
        objects.put(treasureInChest2In4.getName(), treasureInChest2In4);
    }

    private void populateRoom5() {
        populateRoom5a();

        populateRoom5b();
    }

    private void populateRoom5a() {
        // room 5

        door5to2 = new LinkingLockedDoor("door5to2", room5, 5, room2, door2to5state);
        objects.put(door5to2.getName(), door5to2);

        //antidoteIn5 = new Antidote("antidoteIn5", room5, poisoned);
        antidoteIn5 = new GeneralMovableObject("antidoteIn5", room5);
        BeUsed usedAction = new BeUsed(antidoteIn5, antidoteIn5.getParentState(), 1);
        antidoteIn5.addAction(new TriggerActionHandlerByName(
                antidoteIn5, usedAction, poisoned, playerNames, antidoteTriggeredValues, 0, "All your ailments are healed!"));
        antidoteIn5.addAction(usedAction);
        objects.put(antidoteIn5.getName(), antidoteIn5);

        chest1In5 = new Chest("chest1In5", room5);
        objects.put(chest1In5.getName(), chest1In5);
    }

    private void populateRoom5b() {
        keyTo4 = new Key("keyTo4", chest1In5, 4);
        objects.put(keyTo4.getName(), keyTo4);

        chest2In5 = new Chest("chest2In5", room5);
        objects.put(chest2In5.getName(), chest2In5);

        //poisonIn5 = new Poison("poisonIn5", chest2In5, killedByPoison, poisoned);
        //objects.put(poisonIn5.getName(), poisonIn5);

        poisonIn5 = new GeneralMovableObject("poisonIn5", chest2In5);
        chest2In5.addAction(new TriggerActionHandlerByName(chest2In5,
                new BeOpened(null, null), poisoned, playerNames, poisonedTriggeredValues, 0));
        List<ActionHandler> actions = new LinkedList<>();
        actions.add(new ConditionalActionHandlerChecksByName(poisonIn5,
                new TriggerActionHandlerByName(poisonIn5,
                        new BeMoved(null, null), killedByPoison, playerNames, killedByPoisonTriggeredValues, -1),
                poisoned, poisonedTriggeredValues, playerNames, -1));
        chest2In5.addAction(new BeOpenedAddsActionsToOpener(chest2In5, actions, "You feel weak!"));
        objects.put(poisonIn5.getName(), poisonIn5);
    }

    @Override
    protected Player configPlayer(String playerId, String type) {
        Player player = new Player("player" + Integer.toString(players.size() + 1), null, new ChildrenStateLimitedSize(2));
        List<AbstractCondition> winConds = winConditionsPerPlayer.get(playerId);
        List<AbstractCondition> looseConds = looseConditionsPerPlayer.get(playerId);
        Set<String> commands = commandsPerPlayer.get(playerId);

        player.setParent(room1);
        room1.addChild(player);

        ActionHandler pickAction = new Pick(player);
        player.addAction(pickAction);
        commands.add(pickAction.getName());

        ActionHandler openAction = new Open(player);
        player.addAction(openAction);
        commands.add(openAction.getName());

        ActionHandler useAction = new Use(player);
        player.addAction(useAction);
        commands.add(useAction.getName());

        player.addAction(new HaveMovedFrom(player, player.getChildrenState()));
        ActionHandler leaveAction = new Leave(player);
        player.addAction(leaveAction);
        commands.add(leaveAction.getName());

        winConds.add(new ConditionCompound(new ConditionCheckContains(player.getChildrenState(), treasureInChest2In4.getName(), true),
                new ConditionCheckContains(room1.getChildrenState(), player.getName(), true)));

        playerNames.add(player.getName());
        BooleanState myKilledByPoison = new BooleanState(false);
        killedByPoison.add(myKilledByPoison);
        killedByPoisonTriggeredValues.add(Boolean.TRUE);
        poisoned.add(new BooleanState(false));
        poisonedTriggeredValues.add(Boolean.TRUE);
        antidoteTriggeredValues.add(Boolean.FALSE);

        looseConds.add(new ConditionCheckBoolean(myKilledByPoison, true));

        return player;
    }

    @Override
    protected void removePlayerItems(String playerId) {
        Player player = players.get(playerId);
        for (GameObjectWithParent o : player.getChildren()) {
            o.setParent(player.getParent());
            player.getParent().addChild(o);
        }

        int playerIndex = playerNames.indexOf(player.getName());
        playerNames.remove(playerIndex);
        killedByPoison.remove(playerIndex);
        killedByPoisonTriggeredValues.remove(playerIndex);
        poisoned.remove(playerIndex);
        poisonedTriggeredValues.remove(playerIndex);
        antidoteTriggeredValues.remove(playerIndex);
    }

    @Override
    protected void updateGameAfterHandle(String playerId) {

    }

    @SuppressWarnings("CPD-END")

    /*@Override
    public boolean checkWinCondition() {
        return player.contains(treasureInChest2In4.getName()) && room1.contains(player.getName());
    }

    @Override
    public boolean checkLooseCondition() {
        return killedByPoison.isTrue();
    }*/

    @Override
    public ar.fiuba.tdd.tp.model.Game build() {

        createRooms();

        initBooleans();

        populateRoom1();

        populateRoom2();

        populateRoom3();

        populateRoom4();

        populateRoom5();

        //createPlayer();
        return this;
    }
}
