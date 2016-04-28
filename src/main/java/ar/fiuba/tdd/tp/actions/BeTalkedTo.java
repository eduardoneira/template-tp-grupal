package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class BeTalkedTo extends ActionHandler {

    protected int idObjectWhoTalks = 0;
    protected int argsSize = 1;

    public BeTalkedTo(GameObject instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        return "Hi!";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved) {
        if (objectsInvolved.size() != argsSize) {
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return "be talked to";
    }
}
