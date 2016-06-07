package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.ParentState;

import java.util.List;


public class BeUsed extends ActionHandler {

    private int idObjectWhoUses = 0;
    private final int maxAmountOfUses;
    private int currentAmountOfUses;
    private final ParentState holder;

    public BeUsed(GameObject instance, ParentState holder, int maxAmountOfUses) {
        super(instance, 1);
        this.holder = holder;
        this.maxAmountOfUses = maxAmountOfUses;
        this.currentAmountOfUses = 0;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        currentAmountOfUses++;
        return "";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {

        GameObject objectWhoUses = objectsInvolved.get(idObjectWhoUses);
        if (!(holder.getParent().equals(objectWhoUses))) {
            response.append(objectWhoUses.getName());
            response.append(" does not have ");
            response.append(this.instance.getName());
            return false;
        }

        if (currentAmountOfUses == maxAmountOfUses) {
            response.append(this.instance.getName());
            response.append(" has no more uses left");
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return "be used";
    }
}
