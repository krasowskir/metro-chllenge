package uebung.multithreading.firstSteps;


public class ThreadsDemo3 {
    private Thread t;
    private int count;

    public ThreadsDemo3(final String mesg, int n) {
        count = n;
        t = new Thread(new Runnable() {

            public void run() {
                while (count-- > 0) {
                    System.out.println(mesg);
                    try {
                        Thread.sleep(1000); // 100 msec
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                System.out.println(mesg + " thread all done.");
            }
        });
        t.setName(mesg + " runner Thread");
        t.start();
    }
}
