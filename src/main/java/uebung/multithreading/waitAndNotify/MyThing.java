package uebung.multithreading.waitAndNotify;

public class MyThing {

    public synchronized void waiterMethod(String name) throws InterruptedException {
        System.out.println(name + " joined waiterMethod");
        Thread.sleep(1000);
        wait();
        Thread.sleep(1000);
        System.out.println(name + " left waiterMethod");
    }

    public synchronized void notifierMethod(String name) throws InterruptedException {
        System.out.println(name + " joined notifierMethod");
        Thread.sleep(1000);
        notifyAll();
        Thread.sleep(1000);
        System.out.println(name + " left notifierMethod");
    }

    public synchronized void relatedMethod(String name) throws InterruptedException {
        System.out.println(name + " did related work");
        Thread.sleep(1000);
    }
}
