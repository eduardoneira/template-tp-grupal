package ar.fiuba.tdd.tp.abilities;

/**
 * Created by Master on 27/04/2016.
 */
public interface CanHaveLock {
    void lock(int key);

    void unlock(int key);
}
