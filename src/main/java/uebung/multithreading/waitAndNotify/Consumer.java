package uebung.multithreading.waitAndNotify;

public class Consumer implements Runnable {

    Producer producer;

    public Consumer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void run() {
        while (true){
            String msg = this.producer.getMessage();
            System.out.println("got message: " + msg);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
