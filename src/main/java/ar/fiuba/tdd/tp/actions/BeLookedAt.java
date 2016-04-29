package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class BeLookedAt extends ActionHandler {

    private int argsSize = 1;

    public BeLookedAt(GameObject instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        return instance.getName();
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        return objectsInvolved.size() == argsSize;
    }

    @Override
    public String getName() {
        return "be looked at";
    }
}
