package ar.fiuba.tdd.tp.newactions.pick;

import ar.fiuba.tdd.tp.newactions.move.Move;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Master on 27/04/2016.
 */
public class Pick extends Move {

    private static int OBJECT_TO_MOVE = 0;
    private static int ARGS_SIZE = 1;

    public Pick(GameObject instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        GameObject whereToMove = this.instance;
        objectsInvolved.add(whereToMove);

        super.handleAction(getName(), objectsInvolved);

        GameObject objectToMove = objectsInvolved.get(OBJECT_TO_MOVE);
        return "picked " + objectToMove.getName();
    }

    @Override
    public String getName() {
        return "pick";
    }
}
