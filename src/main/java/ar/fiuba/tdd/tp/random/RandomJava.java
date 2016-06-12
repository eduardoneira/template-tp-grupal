package ar.fiuba.tdd.tp.random;

import java.util.Random;

/**
 * Created by Master on 12/06/2016.
 */
public class RandomJava implements RNG {

    Random rand;

    public RandomJava() {
        rand = new Random();
    }

    @Override
    public int getRandomNumber(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
