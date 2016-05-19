package ar.fiuba.tdd.tp.objects.concrete;


import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.objects.general.ConcreteGameObjectWithParentAndChildren;
import ar.fiuba.tdd.tp.objects.general.GameObject;
import ar.fiuba.tdd.tp.objects.general.GameObjectWithChildren;
import ar.fiuba.tdd.tp.objects.states.ChildrenStatePile;

public class Pile extends ConcreteGameObjectWithParentAndChildren {

    public Pile(String name, GameObjectWithChildren parent) {
        super(name, parent, new ChildrenStatePile());
        addAction(new HaveMovedFrom(this, children));
        addAction(new HaveMovedTo(this, children));
        addAction(new BeAskedWhat(this));
        addAction(new BeLookedAt(this));
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
