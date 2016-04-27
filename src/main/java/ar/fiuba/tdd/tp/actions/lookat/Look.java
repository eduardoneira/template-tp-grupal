package ar.fiuba.tdd.tp.actions.lookat;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;

public class Look extends ActionHandler {

    private static int OBJECT_TO_LOOK_AT = 0;
    private static int ARGS_SIZE = 1;

    public Look(GameObject instance) {
        super(instance);
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if(objectsInvolved.size() != ARGS_SIZE) {
            return false;
        }

        GameObject objectToLookAt = objectsInvolved.get(OBJECT_TO_LOOK_AT);
        GameObject whoLooks = this.instance;

        List<GameObject> objectsInvolvedForObjectToLookAt = new LinkedList<GameObject>();
        objectsInvolvedForObjectToLookAt.add(whoLooks);

        return objectToLookAt.canHandleAction("be looked at", objectsInvolvedForObjectToLookAt);
    }

    @Override
    public String getName() {
        return "look";
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        if(!canHandleAction(actionName, objectsInvolved)){
            return "invalid command";
        }

        GameObject whoLooks = this.instance;
        GameObject objectToLookAt = objectsInvolved.get(OBJECT_TO_LOOK_AT);
        StringBuilder builder = new StringBuilder();

        List<GameObject> objectsInvolvedForObjectToLookAt = new LinkedList<GameObject>();
        objectsInvolvedForObjectToLookAt.add(whoLooks);

        builder.append("there're ");
        builder.append( objectToLookAt.handleAction("be looked at", objectsInvolvedForObjectToLookAt ));

        return builder.toString();
    }
}
