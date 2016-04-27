package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.general.GameObject;

import java.util.List;

public class BinaryActionChecker {

    public static boolean check(List<GameObject> objectsInvolved, Class<?> firstClass, Class<?> secondClass) {
        return ((!(objectsInvolved.size() == 2))
                || (! firstClass.isInstance(objectsInvolved.get(0)))
                || (! secondClass.isInstance(objectsInvolved.get(1))
                ));
    }
}
