package ar.fiuba.tdd.tp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Master on 01/06/2016.
 */
public class ConditionCompound implements AbstractCondition {

    List<AbstractCondition> conditions;

    public ConditionCompound(AbstractCondition... conditions) {
        this.conditions = new ArrayList<>();
        Collections.addAll(this.conditions, conditions);
    }

    @Override
    public boolean checkCondition() {
        boolean result = true;
        for (AbstractCondition condition : conditions) {
            result &= condition.checkCondition();
        }
        return result;
    }
}
