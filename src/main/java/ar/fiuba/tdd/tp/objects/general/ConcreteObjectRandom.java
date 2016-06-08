package ar.fiuba.tdd.tp.objects.general;
import java.util.Random;
/**
 * Created by fernando on 08/06/16.
 */
public class ConcreteObjectRandom {

    public static int getRandomNaturalNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

}
