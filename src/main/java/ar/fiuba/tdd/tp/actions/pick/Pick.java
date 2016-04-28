package ar.fiuba.tdd.tp.actions.pick;

import ar.fiuba.tdd.tp.actions.move.Move;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import java.util.List;

public class Pick extends Move {

    protected static int OBJECT_TO_MOVE = 0;
    protected static int ARGS_SIZE = 1;

    public Pick(ConcreteGameObjectWithChildren instance) {
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
