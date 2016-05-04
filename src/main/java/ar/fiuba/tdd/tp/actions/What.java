package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class What extends ActionHandler {

    private int idWhoToAsk = 0;
    //private int argsSize = 1;

    public What(GameObject instance) {
        super(instance, 1);
    }

    @Override
    public String getName() {
        return "what";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        if (objectsInvolved.size() != argsSize) {
            setResponseError(objectsInvolved, response);
            return false;
        }
        return true;
    }

    private void setToResponse(Set<String> actionNoDuplicates, StringBuilder builder) {
        for (String actionName : actionNoDuplicates) {
            builder.append(actionName);
            builder.append(" ");
        }
    }

    public void checkActions(GameObject objectToAsk, StringBuilder builder) {
        Set<String> actionNoDuplicates = new HashSet<>();
        for (ActionHandler myAction : this.instance.getActions()) {
            for (ActionHandler hisAction : objectToAsk.getActions()) {
                if (myAction.causes(hisAction.getName())) {
                    actionNoDuplicates.add(myAction.getName());
                }
            }
        }

        setToResponse(actionNoDuplicates, builder);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        StringBuilder response = new StringBuilder();
        /*if (!canHandleAction(actionName, objectsInvolved, response)) {
            return response.toString();
        }*/

        GameObject objectToAsk = objectsInvolved.get(idWhoToAsk);
        response.append("You can ");
        checkActions(objectToAsk, response);

        return response.toString();
    }
}
