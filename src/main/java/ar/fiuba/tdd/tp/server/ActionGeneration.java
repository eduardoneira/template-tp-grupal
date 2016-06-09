package ar.fiuba.tdd.tp.server;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Thread.sleep;

public class ActionGeneration implements Runnable{

    private static List<ActionWithTime> actions;
    private boolean serverRunning;
    static final int oneMinute = 60000; //one minute = 60000 ms

    public void killActionGeneration(){ serverRunning = false; }
    public void setActionWithTime(ActionWithTime actionWithTime ){
        actions.add(actionWithTime);
    }
    private List<ActionWithTime> getActionsToDo (int currentTime){
        List<ActionWithTime> actionsInTime = new LinkedList<ActionWithTime>();
        if (actions.isEmpty()){
            return null;
        }
        for (int i=0;i<actions.size();i++){
            if (isDivisor(actions.get(i).getTime(),currentTime)){
                actionsInTime.add(actions.get(i));
            }
        }
        return actionsInTime;
    }
    private boolean isDivisor(int divisor, int numero) { //4 16
        int resto = numero % divisor;
        return resto == 0;
    }

    @Override
    public void run() {
        serverRunning = true;
        while (serverRunning){
            long currentTimeMilis = System.currentTimeMillis();
            float currentTimeSecs = currentTimeMilis / 1000;
            int timeMin  = (int) (currentTimeSecs / 60);
            List<ActionWithTime> actionsTodo = (LinkedList<ActionWithTime>) getActionsToDo(timeMin);
            while (!actionsTodo.isEmpty()){
                ActionWithTime actual = actionsTodo.get(actionsTodo.size());
                //escupir esta accion
            }
            try {
                sleep(oneMinute);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
