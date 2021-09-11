package uebung.multithreading.syncAndLock;

import uebung.multithreading.syncAndLock.MeinObject;

public class MeinSynchronizedThread extends Thread{
    private MeinObject myLockedObj;


    public MeinSynchronizedThread(String name, MeinObject myLockedObj){
        this.setName(name);
        this.setMyLockedObj(myLockedObj);
    }

    public MeinObject getMyLockedObj() {
        return myLockedObj;
    }

    public void setMyLockedObj(MeinObject myLockedObj) {
        this.myLockedObj = myLockedObj;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void run() {
        myLockedObj.nutzeMich(this.getName());
    }
}
