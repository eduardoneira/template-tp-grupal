package ar.fiuba.tdd.tp.Actions;

import ar.fiuba.tdd.tp.Objects.GameObject;

import java.util.List;

/**
 * Created by Master on 26/04/2016.
 */
public class Move implements Action {

    @Override
    public String doAction(List<GameObject> objectsInvolved) {
        // ej: move key to player
        // objs[0] es el que mueve, objs[1] es el objeto a mover, objs[2] es el objeto a donde moverlo
        // por ahora asumo que cualquiera puede mover objetos, no chequeo objs[0]
        if (!(objectsInvolved.size() == 3) || !(objectsInvolved.get(1) instanceof CanBeMoved) || !(objectsInvolved.get(2) instanceof CanHaveThingsMovedTo)) {
            return "invalid command";
        }
        // tal vez deberia ser objectThatDoesTheMoving.move(objectToMove, objectToMoveTo)
        CanBeMoved objectToMove = (CanBeMoved) objectsInvolved.get(1);
        CanHaveThingsMovedTo objectToMoveTo = (CanHaveThingsMovedTo) objectsInvolved.get(2);

        return objectToMove.moveTo(objectToMoveTo);
    }

    @Override
    public String getName() {
        return "move";
    }
}