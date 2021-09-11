package uebung.multithreading.syncAndLock;

public class MeinStaticSynchThread extends Thread{


    public MeinStaticSynchThread(String name){
        this.setName(name);
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        StaticSyncObject.nutzeMich(this.getName());
    }
}
