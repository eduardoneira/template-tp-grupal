package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

/**
 * Created by Master on 28/04/2016.
 */
public class BeCursed extends ActionHandler {
    public BeCursed(GameObject instance) {
        super(instance, 0);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        return ""; // nunca deberia llamarse, es un status effect
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        return true;
    }

    @Override
    public String getName() {
        return "be cursed";
    }
}
