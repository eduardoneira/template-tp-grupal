package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.objects.states.BooleanState;

/**
 * Created by Master on 01/06/2016.
 */
public class ConditionCheckBoolean implements AbstractCondition {

    private BooleanState state;
    private boolean expectedValue;

    public ConditionCheckBoolean(BooleanState state, boolean expectedValue) {
        this.state = state;
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean checkCondition() {
        return state.getValue() == expectedValue;
    }
}
