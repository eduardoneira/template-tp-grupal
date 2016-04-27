package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.newactions.pick.Pick;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.concrete.Stick;

public class FetchQuest extends Game {
    public FetchQuest() {
        super("Fetch Quest");
        String nameRoom = "room";
        String nameStick = "stick";

        Room cuarto = new Room(nameRoom);

        player.placeInRoom(cuarto);
        keywords.add(nameStick);

        Pick actionPickup = new Pick(player);
        //LookAt actionLookaround = new LookAt();

        player.addAction(new Pick(player));
        //player.addAction(actionLookaround);

        keywords.add(actionPickup.getName());
        keywords.add(nameRoom);
        keywords.add(nameStick);

        //keywords.add(actionLookaround.getName());
    }
}