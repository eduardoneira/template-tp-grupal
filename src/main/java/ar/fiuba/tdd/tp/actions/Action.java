package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.objects.GameObject;

import java.util.List;

public interface Action {

    String doAction(List<GameObject> objectsInvolved);

    String getName();
}
