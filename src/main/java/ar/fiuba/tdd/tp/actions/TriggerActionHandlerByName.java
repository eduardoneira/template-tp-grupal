package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.List;

/**
 * Created by Master on 07/06/2016.
 */
public class TriggerActionHandlerByName extends TriggerActionHandler {

    protected List<String> names;
    protected int index;

    public TriggerActionHandlerByName(GameObject instance, ActionHandler action, List<BooleanState> triggerables,
                                      List<String> names, List<Boolean> triggeredValues, int index) {
        this(instance, action, triggerables, names, triggeredValues, index, "");
    }

    public TriggerActionHandlerByName(GameObject instance, ActionHandler action, List<BooleanState> triggerables,
                                      List<String> names, List<Boolean> triggeredValues, int index, String retMessage) {
        super(instance, action, triggerables, triggeredValues, retMessage);
        this.names = names;
        this.index = index;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        for (int i = 0; i < triggerables.size(); ++i) {
            if ((index < 0 && names.get(i).equals(this.instance.getName()))
                    || (index >= 0 && names.get(i).equals(objectsInvolved.get(index).getName()))) {
                (triggerables.get(i)).setValue(triggeredValues.get(i));
            }
        }
        //return "triggered booleans";
        return retMessage;
    }
}
