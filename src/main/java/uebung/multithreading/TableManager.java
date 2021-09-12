package uebung.multithreading;

import java.util.Stack;

public class TableManager {
    private Stack<Chopstick> chopsticks = new Stack<>();

    public TableManager() {
        for ( int i = 0; i< 5; i++){
            this.chopsticks.push(new Chopstick());
        }
    }

    public synchronized Chopstick tryToAcquireLeft() throws InterruptedException {
        while (this.chopsticks.size() <= 1){
            wait();
        }
        return chopsticks.pop();
    }

    public synchronized Chopstick tryToAcquireRight() throws InterruptedException {
        while (this.chopsticks.size() == 0){
            wait();
        }
        return chopsticks.pop();
    }

    public synchronized void giveBackChopstick(Chopstick giveBack){
        this.chopsticks.push(giveBack);
        notify();
    }
}
