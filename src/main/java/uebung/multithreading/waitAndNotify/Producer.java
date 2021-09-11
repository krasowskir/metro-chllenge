package uebung.multithreading.waitAndNotify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Producer implements Runnable {

    static final int MAX_QUEUE = 5;
    private List messages = new ArrayList();

    @Override
    public void run() {
        while (true){
            putMessage();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void putMessage(){
        while (messages.size() >= MAX_QUEUE){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        messages.add(new Date().toString());
        notify();
    }

    public synchronized String getMessage(){
        while (messages.size() == 0){
            try {
                notify();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String message = (String)messages.remove(0);
        notify();
        return message;
    }


}
