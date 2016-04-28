package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Master on 27/04/2016.
 */
public class BeTalkedToThief extends BeTalkedTo {

    public BeTalkedToThief(GameObject instance) {
        super(instance);
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {

        GameObject objectWhoTalks = objectsInvolved.get(object_Who_Talks);
        List<GameObject> whoSteals = new LinkedList<>();
        whoSteals.add(this.instance);
        objectWhoTalks.handleAction("have everything stolen", whoSteals);

        return "Hi!";
    }
}
