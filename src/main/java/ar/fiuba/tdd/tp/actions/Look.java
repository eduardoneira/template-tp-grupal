package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;

public class Look extends ActionHandler {

    private int OBJECT_TO_LOOK_AT = 0;
    private int ARGS_SIZE = 1;
    private String BE_LOOKED_AT = "be looked at";

    public Look(GameObject instance) {
        super(instance);
        actionsCaused.add(BE_LOOKED_AT);
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if (objectsInvolved.size() != ARGS_SIZE) {
            return false;
        }

        GameObject whoLooks = this.instance;
        GameObject objectToLookAt = objectsInvolved.get(OBJECT_TO_LOOK_AT);
        List<GameObject> objectsInvolvedForObjectToLookAt = new LinkedList<GameObject>();


        objectsInvolvedForObjectToLookAt.add(whoLooks);

        return objectToLookAt.canHandleAction(BE_LOOKED_AT, objectsInvolvedForObjectToLookAt);
    }

    @Override
    public String getName() {
        return "look";
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        if (!canHandleAction(actionName, objectsInvolved)) {
            return "invalid command";
        }

        GameObject whoLooks = this.instance;
        GameObject objectToLookAt = objectsInvolved.get(OBJECT_TO_LOOK_AT);
        StringBuilder builder = new StringBuilder();

        List<GameObject> objectsInvolvedForObjectToLookAt = new LinkedList<GameObject>();
        objectsInvolvedForObjectToLookAt.add(whoLooks);

        builder.append("there're ");
        builder.append( objectToLookAt.handleAction(BE_LOOKED_AT, objectsInvolvedForObjectToLookAt ));

        return builder.toString();
    }
}
