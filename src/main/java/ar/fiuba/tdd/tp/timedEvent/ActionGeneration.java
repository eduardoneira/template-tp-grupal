package ar.fiuba.tdd.tp.timedevent;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

import static java.lang.Thread.sleep;

public class ActionGeneration implements Runnable{

    private List<ActionWithTime> actions;
    private List<Integer> timeToNextEvent;
    private boolean serverRunning;
    static final int oneMinute = 1000; //one minute = 60000 ms
    private Map<String, Socket> clientSockets;
    int lastTime;
    private TimerReference timer;

    public ActionGeneration(Map<String, Socket> clientSockets, TimerReference timer) {
        actions = new ArrayList<>();
        timeToNextEvent = new ArrayList<>();
        this.clientSockets = clientSockets;
        this.timer = timer;
    }

    public void killActionGeneration() {
        serverRunning = false;
    }

    public void addActionWithTime(ActionWithTime actionWithTime ) {
        actions.add(actionWithTime);
        timeToNextEvent.add(actionWithTime.getTime());
    }

    private void updateTimesToNextEvent(int elapsed) {
        for (int i = 0; i < timeToNextEvent.size(); ++i) {
            timeToNextEvent.set(i, timeToNextEvent.get(i) - elapsed);
        }
    }

    @Override
    public void run() {
        serverRunning = true;
        lastTime = timer.currentTimeSeconds();

        while (serverRunning) {
            int currTime = timer.currentTimeSeconds();
            int elapsed = currTime - lastTime;
            lastTime = currTime;
            updateTimesToNextEvent(elapsed);
            //System.out.println("veo timed events");

            for (int i = 0; i < timeToNextEvent.size(); ++i) {
                if (timeToNextEvent.get(i) <= 0) {
                    ActionWithTime actual = actions.get(i);
                    StringBuilder response = new StringBuilder();

                    // TODO: negrada para enviar el mensaje a todos los jugadores
                    if (actual.getAction().doEvent(response)) {
                        try {
                            for (Socket s : clientSockets.values()) {
                                PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8"));
                                out.println("juego: " + response.toString());
                                out.flush();
                                //out.close();
                            }
                        } catch (IOException e) {

                        }
                    }
                    //System.out.println(currTime + "/" + timeToNextEvent.get(i) + ": timed event: " + response.toString());
                    timeToNextEvent.set(i, actual.getTime());
                }
            }

            int minTime = Collections.min(timeToNextEvent);

            try {
                sleep(timer.getSleepTime(minTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
