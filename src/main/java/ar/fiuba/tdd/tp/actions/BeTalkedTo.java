package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class BeTalkedTo extends ActionHandler {

    protected static final int idObjectWhoTalks = 0;
    protected String dialog;
    //protected int argsSize = 1;

    public BeTalkedTo(GameObject instance) {
        this(instance, "Hi!");
    }

    public BeTalkedTo(GameObject instance, String dialog) {
        super(instance, 1);
        this.dialog = dialog;
    }

    @Override
    public String getName() {
        return "be talked to";
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        return true;
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        return dialog;
    }
}
