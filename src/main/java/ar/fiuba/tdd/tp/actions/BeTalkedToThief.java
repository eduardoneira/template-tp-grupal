package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;

public class BeTalkedToThief extends BeTalkedTo {

    public BeTalkedToThief(GameObject instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        GameObject objectWhoTalks = objectsInvolved.get(idObjectWhoTalks);
        List<GameObject> whoSteals = new LinkedList<>();
        whoSteals.add(this.instance);
        objectWhoTalks.handleAction("have everything stolen", whoSteals);

        return "Hi! The thief just stole all your objects!";
    }
}
