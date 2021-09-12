package uebung.multithreading;

import java.util.Stack;

public class TableManager {
    private Stack<String> chopsticks = new Stack<>();

    public TableManager() {
        for ( int i = 0; i< 5; i++){
            this.chopsticks.push("Besteck");
        }
    }

    public synchronized String tryToAcquireLeft() throws InterruptedException {
        while (this.chopsticks.size() <= 1){
            wait();
        }
        return chopsticks.pop();
    }

    public synchronized String tryToAcquireRight() throws InterruptedException {
        while (this.chopsticks.size() == 0){
            wait();
        }
        return chopsticks.pop();
    }

    public synchronized void giveBackChopstick(String giveBack){
        this.chopsticks.push(giveBack);
        notify();
    }
}
