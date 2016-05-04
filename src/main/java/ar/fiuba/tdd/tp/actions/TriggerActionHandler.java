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
    protected ActionHandler action;
    protected String retMessage;

    public TriggerActionHandler(GameObject instance, ActionHandler action,
                                List<BooleanState> triggerables, List<Boolean> triggeredValues) {
        this(instance, action, triggerables, triggeredValues, "");
    }

    public TriggerActionHandler(GameObject instance, ActionHandler action,
                                List<BooleanState> triggerables, List<Boolean> triggeredValues, String retMessage) {
        super(instance, action.argsSize);
        this.action = action;
        this.triggerables = triggerables;
        this.triggeredValues = triggeredValues;
        this.retMessage = retMessage;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        for (int i = 0; i < triggerables.size(); ++i) {
            (triggerables.get(i)).setValue(triggeredValues.get(i).booleanValue());
        }
        //return "triggered booleans";
        return retMessage;
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
