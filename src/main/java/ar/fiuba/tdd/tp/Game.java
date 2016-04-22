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

    public Game(String n){
        this.name = n;

        // TODO: armar factories para los distintos juegos, aca hardcodeo para probar

        String n_room = "room", n_stick = "stick", n_player = "player";

        Room cuarto = new Room(n_room);
        cuarto.addItem(new Stick(n_stick));

        this.player = new Player(n_player);
        PickUp a_pickup = new PickUp();
        player.addAction(a_pickup);
        LookAround a_lookaround = new LookAround();
        player.addAction(a_lookaround);
        player.placeInRoom(cuarto);

        keywords = new HashSet<String>();
        keywords.add(n_room);
        keywords.add(n_stick);
        //keywords.add(n_player); no se usa nunca, pero podria ser
        keywords.add(a_pickup.getName());
        keywords.add(a_lookaround.getName());
    }

    public String processComand(String s) {

        String[] tokens = s.split(" ");
        List<String> parsed = new LinkedList<String>();
        for (String elem : tokens) {
            if (keywords.contains(elem)) {
                parsed.add(elem);
            }
        }
        if(parsed.size() > 0) {
            String command = parsed.get(0);
            parsed.remove(0);

            return player.doAction(command, parsed.toArray(new String[parsed.size()]));
        } else {
            return "invalid command";
        }
    }

}
