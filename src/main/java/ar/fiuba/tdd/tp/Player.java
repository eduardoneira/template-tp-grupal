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

    public Player(String name) {
        super(name);
        inventory = new Inventory(name + "'s inventory");
        actions = new HashMap<String, Action>();
    }

    @Override
    public void receiveItem(TakeableItem obj) {
        inventory.store(obj);
    }

    public void addAction(Action action) {
        actions.put(action.getName(), action);
    }

    public String doAction(String actionName, String[] params) {
        if (actions.containsKey(actionName)) {
            return actions.get(actionName).doAction(this, scene, params);
        } else {
            return "invalid command";
        }
    }

    public void placeInRoom(Room room) {
        this.scene = room;
    }
}
