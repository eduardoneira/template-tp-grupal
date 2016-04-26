package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.GameObject;

import java.util.List;

public class Pick extends Move {

    // esto es lo de antes, podemos cambiarlo y reusar el codigo de antes aunque no sea tan 'lindo' para leer, o de ultima hacer un wrapper
    /*@Override
    public String doAction(CanDoActions doer, CanHaveActionsDoneOn doee, Object[] args) {
        if (!(doer instanceof CanTakeItems) || !(doee instanceof CanHaveItemsTakenFrom)
                || args.length != 1 || !(args[0] instanceof String)) {
            return "invalid arguments to command"; // log error?
        }
        String itemName = (String)args[0];
        ((CanTakeItems) doer).receiveItem(((CanHaveItemsTakenFrom) doee).takeItem(itemName));
        return "taken " + itemName + " from " + doee.getName();
    }*/

    @Override
    public String doAction(List<GameObject> objectsInvolved) {
        if (objectsInvolved.size() == 0) {
            return "No object selected";
        }
        if (!(objectsInvolved.get(0) instanceof CanBeMoved)){
            return "Cannot be moved";
        }
        // ej: pick stick -> move stick player
        objectsInvolved.add(objectsInvolved.get(0));
        return super.doAction(objectsInvolved);
    }

    @Override
    public String getName() {
        return "pick";
    }
}
