package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Pick;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Stick;

public class FetchQuest extends Game {

    Room room;
    Stick stick;

    @SuppressWarnings("CPD-START")

    public FetchQuest() {
        super("Fetch Quest");

        room = new Room("room");
        player.placeInRoom(room);
        room.addChild(player);
        objects.put(room.getName(), this.room);
        keywords.add(room.getName());

        stick = new Stick("stick", room);
        room.addChild(stick);
        stick.setParent(room);
        objects.put(stick.getName(), stick);
        keywords.add(stick.getName());

        Pick actionPickup = new Pick(player);
        player.addAction(actionPickup);
        keywords.add(actionPickup.getName());
    }

    @SuppressWarnings("CPD-END")

    public boolean checkWinCondition() {
        return player.contains("stick");
    }
}