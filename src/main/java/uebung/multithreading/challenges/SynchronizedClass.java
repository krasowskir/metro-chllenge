package uebung.multithreading.challenges;

public class SynchronizedClass {

    private int myAmount;
    private int mySum;

    public SynchronizedClass() {
        this.myAmount = 0;
        this.mySum = 0;
    }

    public synchronized int methodA(int up){
        try {
            Thread.sleep(1000);
            int tmp = myAmount;
            tmp = tmp + up;
            myAmount = tmp;
            Thread.sleep(1000);
            return myAmount;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int methodB(int up){
        try {
            Thread.sleep(1000);
            int tmp = mySum;
            tmp = tmp + up;
            mySum = tmp;
            Thread.sleep(1000);
            return mySum;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
