package ar.fiuba.tdd.tp.actions.leave;

import ar.fiuba.tdd.tp.actions.move.Move;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithChildren;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class Leave extends Move {

    private static int OBJECT_TO_MOVE = 0;
    private static int ARGS_SIZE = 1;

    public Leave(ConcreteGameObjectWithParentAndChildren instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        GameObject whereToMove = this.instance.getParent();
        objectsInvolved.add(whereToMove);

        super.handleAction(getName(), objectsInvolved);

        GameObject objectToMove = objectsInvolved.get(OBJECT_TO_MOVE);
        return "left " + objectToMove.getName();
    }

    @Override
    public String getName() {
        return "leave";
    }

}
