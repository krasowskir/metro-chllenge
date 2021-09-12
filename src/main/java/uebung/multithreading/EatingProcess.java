package uebung.multithreading;

import java.util.concurrent.CountDownLatch;

public class EatingProcess implements Runnable {

    private TableManager manager;
    private String name;
    private CountDownLatch fertig;

    public EatingProcess(TableManager manager, String name, CountDownLatch fertig) {
        this.manager = manager;
        this.name = name;
        this.fertig = fertig;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        Chopstick stick1 = null, stick2 = null;
        try {
            System.out.println(this.name + " setzt sich an Tisch");
            Thread.sleep(200);

            //left chopstick
            stick1 = this.manager.tryToAcquireLeft();
            System.out.println(this.name + " hat ein Essstäbchen");
            Thread.sleep(200);

            //right chopstick
            stick2 = this.manager.tryToAcquireRight();
            System.out.println(this.name + " hat beide Essstäbchen und isst");
            Thread.sleep(2000);
            this.manager.giveBackChopstick(stick1);
            this.manager.giveBackChopstick(stick2);
            this.fertig.countDown();
            System.out.println(this.name + " ist fertig mit Essen :)");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
