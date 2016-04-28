package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.List;

public class BeOpened extends ActionHandler {

    private int objectWhoOpens = 0;
    private int argsSize = 1;

    private BooleanState open;

    public BeOpened(GameObject instance, BooleanState open) {
        super(instance);
        this.open = open;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        open.setTrue();
        return "done";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if (objectsInvolved.size() != argsSize) {
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return "be opened";
    }
}
