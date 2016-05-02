package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.CheckTop;
import ar.fiuba.tdd.tp.actions.MoveTop;
import ar.fiuba.tdd.tp.objects.concrete.Disc;
import ar.fiuba.tdd.tp.objects.concrete.Pile;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import javafx.scene.shape.MoveTo;

public class TorresHanoi extends Game {
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
        nameRoom = "room";
        room = new Room(nameRoom);
        keywords.add(nameRoom);
        player.placeInRoom(room);
        player.addAction(new MoveTop(player));
        player.addAction(new CheckTop(player));
        room.addChild(player);

        torre1 = new Pile("stack1", room);
        torre2 = new Pile("stack2", room);
        torre3 = new Pile("stack3", room);
        disc1 = new Disc("disc1", torre1, 1);
        disc2 = new Disc("disc2", torre1, 2);
        disc3 = new Disc("disc3", torre1, 3);

        torre1.addChild(disc1);
        torre1.addChild(disc2);
        torre1.addChild(disc3);
        setUpObjectsAndKeywords();

    }

    public void setUpObjectsAndKeywords() {
        keywords.add("disc1");
        keywords.add("disc2");
        keywords.add("disc3");
        keywords.add("stack1");
        keywords.add("stack2");
        keywords.add("stack3");
        objects.put("disc1", disc1);
        objects.put("disc2", disc2);
        objects.put("disc3", disc3);
        objects.put("stack1", torre1);
        objects.put("stack2", torre2);
        objects.put("stack3", torre3);
        addActionToKeywords();
    }

    @Override
    public boolean checkWinCondition() {
        return ((torre1.isEmpty()) && (torre2.isEmpty() || torre3.isEmpty()));
    }

}
