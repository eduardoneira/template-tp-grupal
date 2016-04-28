package ar.fiuba.tdd.tp.actions.open;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import java.util.List;

public class BeOpened extends ActionHandler {

    private static int OBJECT_WHO_OPENS = 0;
    private static int ARGS_SIZE = 1;

    private BooleanState open;

    public BeOpened(GameObject instance, BooleanState open) {
        super(instance);
        this.open = open;
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if (objectsInvolved.size() != ARGS_SIZE) {
            return false;
        }
        return true;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        open.setTrue();
        return "done";
    }

    @Override
    public String getName() {
        return "be opened";
    }
}
