package uebung.multithreading.challenges;

public class SynchronizedRunnable  implements Runnable{
    private SynchronizedClass syn;
    private volatile boolean isDone = false;
    private int up;

    public SynchronizedRunnable(SynchronizedClass synchronizedClass, int up) {
        this.syn = synchronizedClass;
        this.up = up;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public void run() {
        while (!isDone){
            System.out.println("value A: " + syn.methodA(up));
//            System.out.println("value: " + syn.methodB(up));
        }
    }
}
