package ar.fiuba.tdd.tp.actions.move;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Master on 27/04/2016.
 */
public class HaveEverythingStolen extends HaveMovedFrom {

    private static int OBJECT_WHO_STEALS = 0;
    private static int ARGS_SIZE = 1;

    public HaveEverythingStolen(GameObject instance, ChildrenState children) {
        super(instance, children);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        GameObject thief = objectsInvolved.get(OBJECT_WHO_STEALS);

        for (GameObject child : children.getChildren() ) {
            children.removeChild(child);
        }

        return "done";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {

        return objectsInvolved.size() == ARGS_SIZE;
    }

    @Override
    public String getName() {
        return "have everything stolen";
    }
}
