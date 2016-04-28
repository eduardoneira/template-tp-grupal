package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Master on 27/04/2016.
 */
public class What extends ActionHandler {

    private int WHO_TO_ASK = 0;
    private int ARGS_SIZE = 1;

    public What(GameObject instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        GameObject objectToAsk = objectsInvolved.get(WHO_TO_ASK);
        List<GameObject> whoAsks = new LinkedList<>();
        whoAsks.add(this.instance);
        return objectToAsk.handleAction("be asked what", whoAsks);

        // estaria mejor que ni le pregunte, me fijo de mis acciones cuales me puede responder
        /*StringBuilder builder = new StringBuilder();
        builder.append("You can ");

        for(ActionHandler action : this.instance.getActions()) {
            if()
                builder.append(action.getName());
            builder.append(" ");
        }*/
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
        return "what";
    }
}
