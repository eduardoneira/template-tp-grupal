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
        this.instance.handleAction("move", involved);

        return "Entered " + room.getParent().getName() + ".";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        if (!super.canIHandleAction(objectsInvolved, response)) {
            return false;
        }

        GameObject whoOpens = objectsInvolved.get(idObjectWhoOpens);
        List<GameObject> involved = new LinkedList<>();
        involved.add(whoOpens);
        involved.add(room.getParent());

        return this.instance.canHandleAction("move", involved, response);
    }
}
