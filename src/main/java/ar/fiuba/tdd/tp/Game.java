package ar.fiuba.tdd.tp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Master on 21/04/2016.
 */
public class Game {

    private Player player;
    private String name;
    private Set<String> keywords;

    public String getName() {
        return name;
    }

    public Game(String name) {
        this.name = name;

        // TODO: armar factories para los distintos juegos, aca hardcodeo para probar

        String nameRoom = "room";
        String nameStick = "stick";
        String namePlayer = "player";

        Room cuarto = new Room(nameRoom);
        cuarto.addObject(new Stick(nameStick));

        this.player = new Player(namePlayer);
        PickUp actionPickup = new PickUp();
        player.addAction(actionPickup);
        LookAround actionLookaround = new LookAround();
        player.addAction(actionLookaround);
        player.placeInRoom(cuarto);

        keywords = new HashSet<String>();
        keywords.add(nameRoom);
        keywords.add(nameStick);
        //keywords.add(n_player); no se usa nunca, pero podria ser
        keywords.add(actionPickup.getName());
        keywords.add(actionLookaround.getName());
    }

    public String processComand(String stringCommand) {

        String[] splitCommand = stringCommand.split(" ");
        List<String> parsedCommand = new LinkedList<String>();
        for (String elem : splitCommand) {
            if (keywords.contains(elem)) {
                parsedCommand.add(elem);
            }
        }
        if (parsedCommand.size() > 0) {
            String command = parsedCommand.get(0);
            parsedCommand.remove(0);

            return player.doAction(command, parsedCommand.toArray(new String[parsedCommand.size()]));
        } else {
            return "invalid command";
        }
    }

}
