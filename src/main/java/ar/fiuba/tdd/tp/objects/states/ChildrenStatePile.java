package ar.fiuba.tdd.tp.objects.states;

import ar.fiuba.tdd.tp.objects.concrete.Disc;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithParent;

import java.util.*;

/**
 * Created by Master on 07/05/2016.
 */
public class ChildrenStatePile extends ChildrenState {

    public ChildrenStatePile() {
        this.children = new LinkedHashMap<>();
    }

    @Override
    public boolean canRemoveChild(String name) {
        if (!contains(name) || !getLastDisc().getName().equals(name)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean canAddChild(GameObjectWithParent discToAdd) {

        if (isEmpty()) {
            return true;
        } else {
            if (!(discToAdd instanceof Disc)) {
                return false;
            }

            int diameter = ((Disc)discToAdd).getDiameter();
            int smallest = getLastDisc().getDiameter();

            if (diameter >= smallest) {
                return false;
            }

            return true;
        }
    }

    private Disc getLastDisc() {
        String name = (String)(children.keySet().toArray())[children.size() - 1];
        return (Disc) children.get(name);
    }
}
