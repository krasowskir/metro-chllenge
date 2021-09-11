package uebung.multithreading.syncAndLock;

public class MeinObject{

    public synchronized void nutzeMich(String name){
        try {
            System.out.println("starte sync meth mit thread: " + name);
            Thread.sleep(1000);
            System.out.println("ende sync meth mit thread: " + name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
