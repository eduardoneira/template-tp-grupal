package ar.fiuba.tdd.tp.newactions.lookat;

import ar.fiuba.tdd.tp.newactions.ActionHandler;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ChildrenState;

import java.util.List;

/**
 * Created by Master on 27/04/2016.
 */
public class BeLookedAt extends ActionHandler {

    private static int WHO_LOOKS = 0;
    private static int ARGS_SIZE = 1;

    public BeLookedAt(GameObject instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        return instance.getName();
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        return objectsInvolved.size() == ARGS_SIZE;
    }

    @Override
    public String getName() {
        return "be looked at";
    }
}
