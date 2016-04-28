package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;

public class Look extends ActionHandler {

    private int object_To_Look_At = 0;
    private int argsSize = 1;
    private String beLookedAt = "be looked at";

    public Look(GameObject instance) {
        super(instance);
        actionsCaused.add(beLookedAt);
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if (objectsInvolved.size() != argsSize) {
            return false;
        }

        GameObject whoLooks = this.instance;
        GameObject objectToLookAt = objectsInvolved.get(object_To_Look_At);
        List<GameObject> objectsInvolvedForObjectToLookAt = new LinkedList<GameObject>();


        objectsInvolvedForObjectToLookAt.add(whoLooks);

        return objectToLookAt.canHandleAction(beLookedAt, objectsInvolvedForObjectToLookAt);
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
        GameObject objectToLookAt = objectsInvolved.get(object_To_Look_At);
        StringBuilder builder = new StringBuilder();

        List<GameObject> objectsInvolvedForObjectToLookAt = new LinkedList<GameObject>();
        objectsInvolvedForObjectToLookAt.add(whoLooks);

        builder.append("there're ");
        builder.append( objectToLookAt.handleAction(beLookedAt, objectsInvolvedForObjectToLookAt ));

        return builder.toString();
    }
}
