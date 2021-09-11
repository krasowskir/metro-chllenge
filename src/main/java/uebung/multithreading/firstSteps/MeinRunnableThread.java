package uebung.multithreading.firstSteps;

public class MeinRunnableThread implements Runnable {

    private int count;
    private String msg;

    public MeinRunnableThread(int count, String msg) {
        super();
        this.count = count;
        this.msg = "This is run by Thread: " + msg;
    }

    @Override
    public void run() {
        while (this.count-- > 0){
            System.out.println(this.msg);
            try {
                Thread.sleep(300);
            } catch (InterruptedException ie){

            }
        }
        System.out.println("all messages printed");
    }
}
