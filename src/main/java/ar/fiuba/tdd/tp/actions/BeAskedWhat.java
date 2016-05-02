package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class BeAskedWhat extends ActionHandler {

    private int argsSize = 1;

    public BeAskedWhat(GameObject instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You can ");
        for (String action : instance.getActionNames()) {
            stringBuilder.append(action);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        if (objectsInvolved.size() != argsSize) {
            setResponseError(objectsInvolved, response);
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return "be asked what";
    }
}
