package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.*;

/**
 * Created by Master on 26/04/2016.
 */
public class CanBeOpenedClosedStrategy implements OpenStrategy {
    CanBeOpenedStrategy openedStrategy;
    CanBeClosedStrategy closedStrategy;

    public CanBeOpenedClosedStrategy(){
        BooleanState state = new BooleanState(false);
        openedStrategy = new CanBeOpenedStrategy(state);
        closedStrategy = new CanBeClosedStrategy(state);
    }

    public CanBeOpenedClosedStrategy(BooleanState state){
        openedStrategy = new CanBeOpenedStrategy(state);
        closedStrategy = new CanBeClosedStrategy(state);
    }

    public String beOpenedBy(CanOpen opener) {
        // tal vez diferenciar si ya estaba abierto?
        openedStrategy.beOpenedBy(opener);
        return ""; // VER
    }

    public String beClosedBy(CanCloseThings closer) {
        closedStrategy.beClosedBy(closer);
        return ""; // VER
    }

    // funciones de control para ser usadas por clases que extiendan esta funcionalidad
    public void setOpen(){
        openedStrategy.setOpen();
        closedStrategy.setOpen(); // redundante
    }

    public void setClosed(){
        openedStrategy.setOpen(); // redundante
        closedStrategy.setClosed();
    }
}
