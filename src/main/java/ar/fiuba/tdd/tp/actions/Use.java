package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Use extends ActionHandler {

    private int idObjectToUse = 0;
    private String beUsed = "be used";
    //GameObjectWithChildren myInstance;

    public Use(GameObject/*WithChildren*/ instance) {
        super(instance, 1);
        //this.myInstance = instance;
        actionsCaused.add(beUsed);
    }

    @Override
    public String getName() {
        return "use";
    }

    @Override
    public String handleAction(String actionName, List<GameObject> objectsInvolved) {
        GameObject objectToUse = objectsInvolved.get(idObjectToUse);
        List<GameObject> listForObjectToUse = new ArrayList<>();
        //listForObjectToUse.add(this.myInstance);
        listForObjectToUse.add(this.instance);
        String usedRet = objectToUse.handleAction(beUsed, listForObjectToUse);
        String myRet = "used " + objectToUse.getName();
        return concatSentences(myRet, usedRet);
    }

    @Override
    protected boolean canIHandleAction(List<GameObject> objectsInvolved, StringBuilder response) {
        GameObject objectToUse = objectsInvolved.get(idObjectToUse);

        /*if (!this.myInstance.contains(objectToUse.getName())) {
            response.append(this.myInstance.getName());
            response.append(" does not have ");
            response.append(objectToUse.getName());
            return false;
        }*/

        List<GameObject> listForObjectToUse = new ArrayList<>();
        //listForObjectToUse.add(this.myInstance);
        listForObjectToUse.add(this.instance);
        return objectToUse.canHandleAction("be used", listForObjectToUse, response);
    }
}
