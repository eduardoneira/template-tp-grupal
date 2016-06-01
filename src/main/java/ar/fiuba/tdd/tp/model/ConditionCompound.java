package ar.fiuba.tdd.tp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Master on 01/06/2016.
 */
public class ConditionCompound implements AbstractCondition {

    List<AbstractCondition> conditions;

    public ConditionCompound(AbstractCondition... conditions) {
        this.conditions = new ArrayList<>();
        for (AbstractCondition condition : conditions) {
            this.conditions.add(condition);
        }
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
