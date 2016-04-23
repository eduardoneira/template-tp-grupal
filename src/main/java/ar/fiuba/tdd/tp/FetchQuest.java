package ar.fiuba.tdd.tp;

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

        PickUp actionPickup = new PickUp();
        LookAround actionLookaround = new LookAround();

        player.addAction(actionPickup);
        player.addAction(actionLookaround);

        keywords.add(nameRoom);
        keywords.add(nameStick);

        keywords.add(actionPickup.getName());
        keywords.add(actionLookaround.getName());
    }

}