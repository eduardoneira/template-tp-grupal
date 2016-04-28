package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.objects.concrete.Disc;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Torre;

/**
 * Created by fernando on 28/04/16.
 */

public class TorresHanoi extends Game {
    Room room;
    Torre torre1;
    Torre torre2;
    Torre torre3;
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

        torre1 = new Torre("torre1");
        torre2 = new Torre("torre2");
        torre3 = new Torre("torre3");
        disc1 = new Disc("disc1",1);
        disc2 = new Disc("disc2",2);
        disc3 = new Disc("disc3",3);

        torre1.addChild(disc1);
        torre1.addChild(disc2);
        torre1.addChild(disc3);

    }

    @Override
    public boolean checkWinCondition() {
        return false;
    }

}
