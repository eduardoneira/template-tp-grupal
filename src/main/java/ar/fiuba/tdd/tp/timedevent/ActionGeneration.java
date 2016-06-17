package ar.fiuba.tdd.tp.timedevent;

import ar.fiuba.tdd.tp.model.Game;
import ar.fiuba.tdd.tp.objects.states.BooleanState;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class ActionGeneration implements Runnable {

    private List<ActionWithTime> actions;
    private List<Integer> timeToNextEvent;
    volatile boolean finished;
    private Map<String, Socket> clientSockets;
    int lastTime;
    private TimerReference timer;
    ReentrantLock lock;
    //int count;

    public ActionGeneration(Map<String, Socket> clientSockets, TimerReference timer, ReentrantLock lock) {
        actions = new ArrayList<>();
        timeToNextEvent = new ArrayList<>();
        this.clientSockets = clientSockets;
        this.timer = timer;
        this.finished = false;
        this.lock = lock;
        //count = 0;
    }

    public void killActionGeneration() {
        lock.lock();
        try {
            finished = true;
            // TODO: descomentar para debugear mejor los tests
            //System.out.println("llego señal de kill: " + finished);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("error aca");
        } finally {
            lock.unlock();
        }
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
        finished = false;
        lastTime = timer.currentTimeSeconds();

        while (!finished) {
            /*if (count < 150) {
                //System.out.println(Thread.getAllStackTraces().keySet());
                System.out.println("action generation is finished: " + finished);
                count++;
            }*/
            // TODO: descomentar para debugear mejor los tests
            //System.out.println("espero lock");
            lock.lock();
            try {
                // TODO: descomentar para debugear mejor los tests
                //System.out.println("tengo lock");
                this.updateEvents();
            } finally {
                lock.unlock();
            }
            this.sleepUntilNextEvent();
        }
        // TODO: descomentar para debugear mejor los tests
        //System.out.println("action generation stops");
    }

    //BEGIN GENERATED CODE
    private void updateEvents() {
        int currTime = timer.currentTimeSeconds();
        int elapsed = currTime - lastTime;
        lastTime = currTime;
        if (elapsed < 0) {
            return;
        }
        updateTimesToNextEvent(elapsed);

        for (int i = 0; i < timeToNextEvent.size(); ++i) {
            if (timeToNextEvent.get(i) <= 0) {
                ActionWithTime actual = actions.get(i);
                StringBuilder response = new StringBuilder();

                if (actual.getAction().doEvent(response)) {
                    forwardEventMessage(response);
                }
                timeToNextEvent.set(i, actual.getTime());
            }
        }
    }
    //END GENERATED CODE

    private void sleepUntilNextEvent() {
        int minTime = getMinTimeToNextEvent();
        long timeToSleep = timer.getSleepTime(minTime);

        if (timeToSleep > 0) {
            try {
                sleep(timeToSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void forwardEventMessage(StringBuilder response) {
        // TODO: descomentar esta linea para debugear mejor las tests
        /*if (count < 150) {
            System.out.println(response);
        }*/
        try {
            for (Socket s : clientSockets.values()) {
                PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8"));
                out.println("juego: " + response.toString());
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getMinTimeToNextEvent() {
        if (timeToNextEvent.isEmpty()) {
            return 1;
        } else {
            return Collections.min(timeToNextEvent);
        }
    }
}
