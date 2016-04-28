package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.actions.ActionHandler;
import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class BeTalkedTo extends ActionHandler {

    protected int OBJECT_WHO_TALKS = 0;
    protected int ARGS_SIZE = 1;

    public BeTalkedTo(GameObject instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        return "Hi!";
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
        return "be talked to";
    }
}
