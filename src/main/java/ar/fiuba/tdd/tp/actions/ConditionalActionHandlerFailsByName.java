package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.util.List;

/**
 * Created by Master on 07/06/2016.
 */
public class ConditionalActionHandlerFailsByName extends ConditionalActionHandlerFails {

    protected List<String> names;
    protected int indexInObjectsInvolved;

    public ConditionalActionHandlerFailsByName(GameObject instance, ActionHandler action, List<BooleanState> conditions, List<String> names, int i) {
        super(instance, action, conditions);
        this.names = names;
        this.indexInObjectsInvolved = i;
    }

    @Override
    protected boolean checkConditions(List<GameObject> objectsInvolved, StringBuilder response) {
        for (int i = 0; i < conditions.size(); ++i) {
            if ((indexInObjectsInvolved >= 0 && objectsInvolved.get(indexInObjectsInvolved).equals(names.get(i)))
                    ||  (indexInObjectsInvolved < 0 && this.instance.getName().equals(names.get(i)) )
                    && (!conditions.get(i).isTrue())) {
                response.append("a condition was not met in conditional command ");
                response.append(getName());
                return false;
            }
        }

        return true;
    }
}
