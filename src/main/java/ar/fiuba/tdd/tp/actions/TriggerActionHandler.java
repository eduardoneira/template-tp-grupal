package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.List;

/**
 * Created by Master on 03/05/2016.
 */
public class TriggerActionHandler extends ActionHandler {

    protected List<BooleanState> triggerables;
    protected List<Boolean> triggeredValues;
    protected ActionHandler action; // podria ser un string con el nombre, porque es lo unico que se usa

    public TriggerActionHandler(GameObject instance, ActionHandler action,
                                List<BooleanState> triggerables, List<Boolean> triggeredValues) {
        super(instance);
        this.action = action;
        this.triggerables = triggerables;
        this.triggeredValues = triggeredValues;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        for (int i = 0; i < triggerables.size(); ++i) {
            (triggerables.get(i)).setValue(triggeredValues.get(i).booleanValue());
        }
        //return "triggered booleans";
        return "";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        return true;
    }

    @Override
    public String getName() {
        return action.getName();
    }
}
