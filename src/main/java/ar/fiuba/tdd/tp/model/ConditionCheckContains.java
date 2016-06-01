package ar.fiuba.tdd.tp.model;

import ar.fiuba.tdd.tp.objects.states.ChildrenState;

/**
 * Created by Master on 01/06/2016.
 */
public class ConditionCheckContains implements AbstractCondition {

    private ChildrenState container;
    private String containeeName;
    private boolean expectedValue;

    public ConditionCheckContains(ChildrenState container, String containeeName, boolean expectedValue) {
        this.container = container;
        this.containeeName = containeeName;
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean checkCondition() {
        return container.contains(containeeName) == expectedValue;
    }
}
