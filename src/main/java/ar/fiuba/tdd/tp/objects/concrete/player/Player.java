package ar.fiuba.tdd.tp.objects.concrete.player;

import ar.fiuba.tdd.tp.actions.HaveMovedTo;
import ar.fiuba.tdd.tp.objects.concrete.Room;
import ar.fiuba.tdd.tp.objects.general.*;
import java.util.*;

public class Player extends ConcreteGameObjectWithChildren {

    private Room scene;

    public Player(String name) {
        super(name);

        addAction(new HaveMovedTo(this, children));
    }

    // esto para mi deberia ir afuera
    public String doAction(String actionName, List<String> params) {
        List<GameObject> objectsInvolved = new ArrayList<GameObject>();

        // siempre el jugador es quien hace la accion
        //objectsInvolved.add(this);
        Iterator<String> it = params.iterator();
        while (it.hasNext()) {
            // Que el inventario se fije si tiene el item, sino lo delegue a room (chain of responsibility)
            String parameter = it.next();
            if (children.contains(parameter)) {
                objectsInvolved.add(children.getChild(parameter));
            } else if (scene.contains(parameter)) {
                objectsInvolved.add(scene.getChild(parameter));
            } else if (scene.getName().equals(parameter)) {
                objectsInvolved.add(scene);
            }
        }

        return handleAction(actionName, objectsInvolved);
    }

    public void placeInRoom(Room room) {
        this.scene = room;
    }

    public Room currentRoom() {
        return this.scene;
    }
}
