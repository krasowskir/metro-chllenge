package uebung.multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
    /*  ein und derselbe thread, kann 1, 2 und x locks auf dem selben Objekt haben
     */
    private Lock lock = new ReentrantLock();
    private String name;

    public synchronized boolean tryAcquire(String name) throws InterruptedException {
        boolean lockSucceeded = false;
        if (!name.equals(this.name)) {
            lockSucceeded = this.lock.tryLock(1000l, TimeUnit.MILLISECONDS);
        }
        if (lockSucceeded) {
            this.name = name;
        }
        return lockSucceeded;
    }

    public String printWhoHoldsLock() {
        return this.name;
    }

    public synchronized void putBack() {
        this.lock.unlock();
        this.name = "";
    }
}
