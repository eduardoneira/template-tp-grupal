package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;
import ar.fiuba.tdd.tp.objects.states.ParentState;

import java.util.LinkedList;
import java.util.List;

public class BeOpenedMovesOpenerToOtherRoom extends BeOpened {

    ParentState room;

    public BeOpenedMovesOpenerToOtherRoom(GameObject instance, BooleanState state, ParentState room) {
        super(instance, state);
        this.room = room;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        GameObject whoOpens = objectsInvolved.get(idObjectWhoOpens);

        List<GameObject> involved = new LinkedList<>();
        involved.add(whoOpens);
        involved.add(room.getParent());
        whoOpens.handleAction("move", involved);

        return "Entered " + room.getParent().getName() + ".";
    }
}
