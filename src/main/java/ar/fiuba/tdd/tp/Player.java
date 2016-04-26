package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.Action;
import ar.fiuba.tdd.tp.objects.ConcreteGameObjectLeaf;
import ar.fiuba.tdd.tp.objects.GameObject;
import ar.fiuba.tdd.tp.objects.Room;

import java.util.*;

public class Player extends ConcreteGameObjectLeaf {

    private Inventory inventory;
    private Map<String, Action> actions;
    private Room scene;

    public Player(String name) {
        super(name);
        inventory = new Inventory(name + "'s inventory");
        actions = new HashMap<String, Action>();
    }

    public void addAction(Action action) {
        actions.put(action.getName(), action);
    }

    public String doAction(String actionName, List<String> params) {
        if (actions.containsKey(actionName)) {
            List<GameObject> objectsInvolved = new ArrayList<GameObject>();
            Iterator<String> it = params.iterator();
            while (it.hasNext()) {
                // Que el inventario se fije si tiene el item, sino lo delegue a room (chain of responsibility)
                String parameter = it.next();
            }
            return actions.get(actionName).doAction(objectsInvolved);
        } else {
            return "invalid command";
        }
    }

    public void placeInRoom(Room room) {
        this.scene = room;
    }
}
