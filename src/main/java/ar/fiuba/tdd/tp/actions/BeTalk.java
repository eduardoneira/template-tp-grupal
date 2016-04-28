package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.concrete.player.Player;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class BeTalk extends ActionHandler {

    private static int OBJECT_WHO_TALKS = 0;
    private static int ARGS_SIZE = 1;

    public BeTalk(GameObject instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        if (objectsInvolved.get(0) instanceof Player){
            Player oPlayer = (Player) objectsInvolved.get(0);
            oPlayer.getChild("damn object");
        }
        return "done";
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
        return "be talk";
    }
}
