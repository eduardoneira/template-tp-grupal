package ar.fiuba.tdd.tp.objects.general;

import java.util.List;

public interface GameObject {

    String getName();
    boolean canHandleAction(String actionName, List<GameObject> objectsInvolved);
    String handleAction(String actionName, List<GameObject> objectsInvolved);
}
