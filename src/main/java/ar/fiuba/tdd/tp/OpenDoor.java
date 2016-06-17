package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Open;
import ar.fiuba.tdd.tp.actions.Pick;
import ar.fiuba.tdd.tp.model.AbstractCondition;
import ar.fiuba.tdd.tp.model.ConditionCheckBoolean;
import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.objects.concrete.Key;
import ar.fiuba.tdd.tp.objects.concrete.Player;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.door.LockedDoor;

import java.util.List;
import java.util.Set;

public class OpenDoor extends Game {

    Room room;
    LockedDoor door;
    Key key;
    String nameRoom = "room";
    String nameKey = "key";
    String nameDoor = "door";

    @SuppressWarnings("CPD-START")
    //BEGIN GENERATED CODE

    public OpenDoor() {
        super("OpenDoor");
    }

    private void createRoom() {
        room = new Room(nameRoom);
        objects.put(room.getName(), room);
    }

    private void createDoor() {
        door = new LockedDoor(nameDoor, room, 1);
        door.setClosed();
        door.setLocked();
        objects.put(door.getName(), door);
    }

    private void createKey() {
        key = new Key(nameKey, room, 1);
        objects.put(key.getName(), key);
    }

    @Override
    protected Player configPlayer(String playerId, String type) {
        Player player = new Player("player" + Integer.toString(players.size() + 1), null);
        Set<String> commands = commandsPerPlayer.get(playerId);
        List<AbstractCondition> winConds = winConditionsPerPlayer.get(playerId);

        player.setParent(room);
        room.addChild(player);

        Open actionOpen = new Open(player);
        player.addAction(actionOpen);
        commands.add(actionOpen.getName());

        Pick actionPickup = new Pick(player);
        player.addAction(actionPickup);
        commands.add(actionPickup.getName());

        winConds.add(new ConditionCheckBoolean(door.getOpenState(), true));

        return player;
    }

    @Override
    protected void updateGameAfterHandle(String playerId) {

    }

    /*@Override
    public boolean checkWinCondition() {
        return door.isOpen();
    }

    @Override
    public boolean checkLooseCondition() {
        return false;
    }*/

    @SuppressWarnings("CPD-END")

    @Override
    public ar.fiuba.tdd.tp.model.Game build() {

        createRoom();

        createDoor();

        createKey();

        //createPlayer();
        return this;
    }
    //END GENERATED CODE
}
