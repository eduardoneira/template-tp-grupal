package ar.fiuba.tdd.tp.abilities;

public interface CanHaveLock {
    void lock(int key);

    void unlock(int key);
}
