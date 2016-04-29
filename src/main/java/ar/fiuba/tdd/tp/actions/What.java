package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class What extends ActionHandler {

    private int idWhoToAsk = 0;
    private int argsSize = 1;

    public What(GameObject instance) {
        super(instance);
    }

    @Override
    public String getName() {
        return "what";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if (objectsInvolved.size() != argsSize) {
            return false;
        }
        return true;
    }

    public void checkActions(GameObject objectToAsk, StringBuilder builder) {
        for (ActionHandler myAction : this.instance.getActions()) {
            for (ActionHandler hisAction : objectToAsk.getActions()) {
                if (myAction.causes(hisAction.getName())) {
                    builder.append(myAction.getName());
                    builder.append(" ");
                }
            }
        }

    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        if (!canIHandleAction(objectsInvolved)) {
            return "invalid command";
        }
        // estaria mejor que ni le pregunte, me fijo de mis acciones cuales me puede responder
        GameObject objectToAsk = objectsInvolved.get(idWhoToAsk);
        StringBuilder builder = new StringBuilder();
        builder.append("You can ");
        checkActions(objectToAsk, builder);

        return builder.toString();

        /*GameObject objectToAsk = objectsInvolved.get(WHO_TO_ASK);
        List<GameObject> whoAsks = new LinkedList<>();
        whoAsks.add(this.instance);
        return objectToAsk.handleAction("be asked what", whoAsks);*/
    }
}
