package ar.fiuba.tdd.tp.objects.concrete;


import ar.fiuba.tdd.tp.actions.HaveMovedFrom;
import ar.fiuba.tdd.tp.actions.HaveMovedTo;
import ar.fiuba.tdd.tp.actions.MoveTop;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Pile extends ConcreteGameObjectWithParentAndChildren {

    public Pile(String name, GameObject parent) {
        super(name, parent);
        addAction(new MoveTop(this));
        addAction(new HaveMovedFrom(this, children));
        addAction(new HaveMovedTo(this, children));
    }

    public int getSmallestDiamater() {
        int smallest = 9999;
        for (GameObject child: this.getChildren()) {
            int diameter = ((Disc)child).getDiameter();
            if (diameter < smallest) {
                smallest = diameter;
            }
        }
        return smallest;
    }

    public Disc getTopDisc() {
        int smallest = 9999;
        Disc smallestDisc = null;
        for (GameObject child: this.getChildren()) {
            int diameter = ((Disc)child).getDiameter();
            if (diameter < smallest) {
                smallest = diameter;
                smallestDisc = (Disc)child;
            }
        }
        return smallestDisc;
    }



}
