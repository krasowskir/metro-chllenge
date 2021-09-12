package uebung.multithreading.challenges;

public class Foo {


    public Foo() {
    }

    public synchronized void first() {
        try {
            Thread.sleep(1000);
            System.out.println("called 1st");
            Thread.sleep(1000);
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void second() {
        try {
            wait(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("called 2nd");
        notify();
    }

    public synchronized void third() {
        try {
            wait(1000L);
            wait(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("called 3rd");
    }
}
