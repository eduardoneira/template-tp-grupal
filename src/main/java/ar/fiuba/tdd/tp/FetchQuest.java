package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.Actions.LookAt;
import ar.fiuba.tdd.tp.Actions.Pick;

/**
 * Created by fernando on 23/04/16.
 */
public class FetchQuest extends Game {
    public FetchQuest() {
        super("Fetch Quest");
        String nameRoom = "room";
        String nameStick = "stick";

        Room cuarto = new Room(nameRoom);
        cuarto.addObject(new Stick(nameStick));

        player.placeInRoom(cuarto);
        keywords.add(nameStick);

        Pick actionPickup = new Pick();
        LookAt actionLookaround = new LookAt();

        player.addAction(actionPickup);
        player.addAction(actionLookaround);

        keywords.add(nameRoom);
        keywords.add(nameStick);

        keywords.add(actionPickup.getName());
        keywords.add(actionLookaround.getName());
    }
}