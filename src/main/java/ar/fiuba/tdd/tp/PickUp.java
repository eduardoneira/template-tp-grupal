package ar.fiuba.tdd.tp;
/**
 * Created by Master on 21/04/2016.
 */
public class PickUp implements Action{

    @Override
    public String doAction(CanDoActions doer, CanHaveActionsDoneOn doee, Object[] args) {
        if (!(doer instanceof CanTakeItems) || !(doee instanceof CanHaveItemsTakenFrom)
                || args.length != 1 || !(args[0] instanceof String)) {
            return "invalid arguments to command"; // log error?
        }
        String itemName = (String)args[0];
        ((CanTakeItems) doer).receiveItem(((CanHaveItemsTakenFrom) doee).takeItem(itemName));
        return "taken " + itemName + " from " + doee.getName();
    }

    @Override
    public String getName() {
        return "pick";
    }
}
