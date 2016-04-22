package ar.fiuba.tdd.tp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Master on 21/04/2016.
 */
public class Player extends ConcreteGameObjectLeaf implements CanTakeItems{

    private Inventory inventory;
    private Map<String, Action> actions;
    private Room scene;

    public Player(String n){
        super(n);
        inventory = new Inventory(n + "'s inventory");
        actions = new HashMap<String, Action>();
    }

    @Override
    public void receiveItem(TakeableItem o) {
        inventory.store(o);
    }

    public void addAction(Action a){
        actions.put(a.getName(), a);
    }

    public String doAction(String a, String[] o){
        if(actions.containsKey(a)){
            return actions.get(a).doAction(this, scene, o);
        } else {
            return "invalid command";
        }
    }

    public void placeInRoom(Room r){
        this.scene = r;
    }
}
