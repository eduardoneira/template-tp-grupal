package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Pick;
import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Stick;

public class FetchQuest extends Game {

    Room room;
    Stick stick;

    @SuppressWarnings("CPD-START")

    public FetchQuest() {
        super("FetchQuest");
    }

    @SuppressWarnings("CPD-END")

    public boolean checkWinCondition() {
        return player.contains("stick");
    }

    @Override
    public boolean checkLooseCondition() {
        return false;
    }

    @Override
    public ar.fiuba.tdd.tp.model.Game build() {
        room = new Room("room");
        player.setParent(room);
        objects.put(room.getName(), this.room);

        stick = new Stick("stick", room);
        stick.setParent(room);
        objects.put(stick.getName(), stick);

        Pick actionPickup = new Pick(player);
        player.addAction(actionPickup);

        commands.add(actionPickup.getName());
        return this;
    }
}