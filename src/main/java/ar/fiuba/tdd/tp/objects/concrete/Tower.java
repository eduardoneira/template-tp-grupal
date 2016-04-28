package ar.fiuba.tdd.tp.objects.concrete;

import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithChildren;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParent;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.*;

/**
 * Created by fernando on 28/04/16.
 */
public class Tower extends ConcreteGameObjectWithParentAndChildren {

    private Stack<GameObject> mTower;
    
    public Tower(String name) {
        super("torre hanoi");
    }

    @Override
    public boolean canHandleAction(String actionName, List<GameObject> objectsInvolved) {
        if (objectsInvolved.isEmpty()) {return false;}
        else{
            if(objectsInvolved.get(0) instanceof Disc) {
                Disc disc = (Disc) objectsInvolved.get(0);
                Disc miDisc = (Disc) mTower.pop();
                if (disc.getDiameter() < miDisc.getDiameter()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<GameObject> getChildren() {
        List<GameObject> list = new LinkedList<>();
        if (!mTower.isEmpty()) {
            list.add(mTower.pop());
        }
        return list;
    }

}