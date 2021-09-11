package uebung.multithreading.syncAndLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockATM {
    private Lock lock;
    private int balance = 100;

    public LockATM() {
        this.lock = new ReentrantLock();
    }

    public synchronized int withdraw(int value){
        lock.lock();
        int tmp = balance;
        try {
            Thread.sleep(1000);
            tmp = tmp - value;
            Thread.sleep(1000);
            balance = tmp;
            System.out.println("new ballance is: "+ balance);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        return tmp;
    }

    public synchronized int deposit(int value){
        lock.lock();
        int tmp = balance;
        try {
            Thread.sleep(1000);
            tmp = tmp + value;
            Thread.sleep(2000);
            balance = tmp;
            System.out.println("new ballance is: "+ balance);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        return tmp;
    }
}
