package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.List;

public class BeOpened extends ActionHandler {

    protected int idObjectWhoOpens = 0;
    //protected int argsSize = 1;

    private BooleanState open;

    public BeOpened(GameObject instance, BooleanState open) {
        super(instance, 1);
        this.open = open;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        open.setTrue();
        return "";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        /*if (objectsInvolved.size() != argsSize) {
            return false;
        }*/
        return true;
    }

    @Override
    public String getName() {
        return "be opened";
    }
}
