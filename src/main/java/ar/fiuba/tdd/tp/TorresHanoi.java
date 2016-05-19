package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.actions.CheckTop;
import ar.fiuba.tdd.tp.actions.MoveTop;
import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.objects.concrete.Disc;
import ar.fiuba.tdd.tp.objects.concrete.Pile;
import ar.fiuba.tdd.tp.objects.concrete.Room;

public class TorresHanoi extends Game {

    @SuppressWarnings("CPD-START")

    Room room;
    Pile torre1;
    Pile torre2;
    Pile torre3;
    Disc disc1;
    Disc disc2;
    Disc disc3;

    String nameRoom;

    public TorresHanoi() {
        super("Hanoi Towers");
    }

    private void createRoom() {
        nameRoom = "room";
        room = new Room(nameRoom);
        objects.put(room.getName(), room);
    }

    private void createPlayer() {
        player.setParent(room);
        room.addChild(player);

        ActionHandler moveTopAction = new MoveTop(player);
        player.addAction(moveTopAction);
        commands.add(moveTopAction.getName());

        ActionHandler checkTopAction = new CheckTop(player);
        player.addAction(checkTopAction);
        commands.add(checkTopAction.getName());
    }

    private void createStacks() {
        torre1 = new Pile("stack1", room);
        objects.put("stack1", torre1);

        torre2 = new Pile("stack2", room);
        objects.put("stack2", torre2);

        torre3 = new Pile("stack3", room);
        objects.put("stack3", torre3);
    }

    private void createDiscs() {

        disc3 = new Disc("disc3", torre1, 3);
        objects.put("disc3", disc3);

        disc2 = new Disc("disc2", torre1, 2);
        objects.put("disc2", disc2);

        disc1 = new Disc("disc1", torre1, 1);
        objects.put("disc1", disc1);
    }

    @SuppressWarnings("CPD-END")

    @Override
    public boolean checkWinCondition() {
        return ((torre1.isEmpty()) && (torre2.isEmpty() || torre3.isEmpty()));
    }

    @Override
    public boolean checkLooseCondition() {
        return false;
    }

    @Override
    public ar.fiuba.tdd.tp.model.Game build() {
        createRoom();

        createPlayer();

        createStacks();

        createDiscs();
        return this;
    }
}
