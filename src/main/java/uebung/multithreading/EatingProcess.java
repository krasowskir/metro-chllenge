package uebung.multithreading;

public class EatingProcess implements Runnable {

    private Challenges.Chopstick[] chopsticks;
    private String name;

    public EatingProcess(Challenges.Chopstick[] chopsticks, String name) {
        this.chopsticks = chopsticks;
        this.name = name;
    }

    @Override
    public void run() {
        Challenges.Chopstick stick1 = null, stick2 = null;
        try {
            System.out.println(this.name + " setzt sich an Tisch");
            Thread.sleep(200);
            boolean notAcquired = true;
            //left chopstick
            while (notAcquired){
                stick1 = grabChopstick(chopsticks, this.name);
                if (stick1 != null){
                    notAcquired = false;
                }
            }
            System.out.println(this.name + " hat ein Essstäbchen");
            Thread.sleep(200);
            //right chopstick
            notAcquired = true;
            while (notAcquired){
                Thread.sleep(500);
                stick2 = grabChopstick(chopsticks, this.name);
                if (stick2 != null){
                    notAcquired = false;
                }
            }
            System.out.println(this.name+" hat beide Essstäbchen und isst");
            Thread.sleep(2000);
            stick2.putBack();
            stick1.putBack();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private Challenges.Chopstick grabChopstick(Challenges.Chopstick[] chopsticks, String threadName) throws InterruptedException {
        for (Challenges.Chopstick elem : chopsticks){
            boolean holdChopstick = elem.tryAcquire(threadName);
            if (holdChopstick){
                return elem;
            }
        }
        return null;
    }
}
