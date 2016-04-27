package ar.fiuba.tdd.tp.objects.concrete.player;

import ar.fiuba.tdd.tp.actions.Action;
import ar.fiuba.tdd.tp.objects.concrete.Inventory;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.general.*;

import java.util.*;

public class Player extends ConcreteGameObject implements GameObjectCanHaveThingsMovedTo {

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
            // siempre el jugador es quien hace la accion
            objectsInvolved.add(this);

            Iterator<String> it = params.iterator();
            while (it.hasNext()) {
                // Que el inventario se fije si tiene el item, sino lo delegue a room (chain of responsibility)
                String parameter = it.next();
                if (inventory.contains(parameter)) {
                    objectsInvolved.add(inventory.getChild(parameter));
                } else if (scene.contains(parameter)) {
                    objectsInvolved.add(scene.getChild(parameter));
                } else if (scene.getName() == parameter) {
                    objectsInvolved.add(scene);
                }
            }
            return actions.get(actionName).doAction(objectsInvolved);
        } else {
            return "player does not have that action";
        }
    }

    public void placeInRoom(Room room) {
        this.scene = room;
    }

    public Room currentRoom() {
        return this.scene;
    }

    @Override
    public String haveMovedTo(GameObjectCanBeMoved toMove) {
        return inventory.haveMovedTo(toMove);
    }

    @Override
    public GameObjectCanHaveParent getChild(String name) {
        return inventory.getChild(name);
    }

    @Override
    public boolean contains(String name) {
        return inventory.contains(name);
    }
}
