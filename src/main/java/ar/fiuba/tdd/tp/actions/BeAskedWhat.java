package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

/**
 * Created by Master on 28/04/2016.
 */
public class BeAskedWhat extends ActionHandler {

    private int WHO_ASKS = 0;
    private int ARGS_SIZE = 1;

    public BeAskedWhat(GameObject instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You can ");
        for(ActionHandler action : instance.getActions()) {
            stringBuilder.append(action.getName());
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if (objectsInvolved.size() != ARGS_SIZE) {
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return "be asked what";
    }
}
