package ar.fiuba.tdd.tp.Actions;

import ar.fiuba.tdd.tp.GameObject;

import java.util.List;

/**
 * Created by Master on 21/04/2016.
 */
public interface Action {

    String doAction(List<GameObject> objectsInvolved);

    String getName();
}
