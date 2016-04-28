package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.objects.concrete.Disc;
import ar.fiuba.tdd.tp.objects.concrete.Pile;
import ar.fiuba.tdd.tp.objects.concrete.Room;

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
        super("torres hanoi");
        nameRoom = "room";
        room = new Room(nameRoom);
        keywords.add(nameRoom);
        player.placeInRoom(room);
        room.addChild(player);

        torre1 = new Pile("stack1");
        torre2 = new Pile("stack2");
        torre3 = new Pile("stack3");
        disc1 = new Disc("disc1",1);
        disc2 = new Disc("disc2",2);
        disc3 = new Disc("disc3",3);

        torre1.addChild(disc1);
        torre1.addChild(disc2);
        torre1.addChild(disc3);
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
        disc1.setParent(torre1);
        disc2.setParent(torre1);
        disc3.setParent(torre1);
    }

    @Override
    public boolean checkWinCondition() {
        return ((torre1.isEmpty()) && (torre2.isEmpty() || torre3.isEmpty()));
    }

}
