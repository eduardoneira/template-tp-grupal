package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.actions.Move;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import java.util.List;

public class Pick extends Move {

    private int OBJECT_TO_MOVE = 0;
    private int ARGS_SIZE = 1;

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
