package ar.fiuba.tdd.tp.random;

/**
 * Created by Master on 12/06/2016.
 */
public class RandomReference implements RNG {

    private RNG instance;

    public RandomReference(RNG instance) {
        setRandom(instance);
    }

    @Override
    public int getRandomNumber(int min, int max) {
        return instance.getRandomNumber(min, max);
    }

    public void setRandom(RNG instance) {
        this.instance = instance;
    }
}
