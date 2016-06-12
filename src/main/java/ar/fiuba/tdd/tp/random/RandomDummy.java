package ar.fiuba.tdd.tp.random;

/**
 * Created by Master on 12/06/2016.
 */
public class RandomDummy implements RNG {

    private int number;

    public RandomDummy(int number) {
        this.number = number;
    }

    @Override
    public int getRandomNumber(int min, int max) {
        return number;
    }
}
