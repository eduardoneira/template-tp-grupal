package ar.fiuba.tdd.tp.objects;

import ar.fiuba.tdd.tp.actions.CanBeOpened;
import ar.fiuba.tdd.tp.actions.CanCloseThings;
import ar.fiuba.tdd.tp.actions.CanOpen;

public class CanBeOpenedStrategy implements OpenStrategy {
    CanBeOpened instance;
    BooleanState open;

    public CanBeOpenedStrategy(){
        open = new BooleanState(false);
    }

    public CanBeOpenedStrategy(BooleanState state){
        open = state;
    }

    public String beOpenedBy(CanOpen opener) {
        // tal vez diferenciar si ya estaba abierto?
        open.setTrue();
        return ""; // VER
    }

    // funciones de control para ser usadas por clases que extiendan esta funcionalidad
    public void setOpen(){
        open.setTrue();
    }

    public void setClosed(){
        open.setFalse();
    }
}
