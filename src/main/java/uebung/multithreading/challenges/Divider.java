package uebung.multithreading.challenges;

public class Divider implements DividibleBy, Runnable {

    private final int DIVIDING_FACTOR;
    private volatile boolean isDone = false;
    private FizzBuzzManager fizzBuzzManager;
    private String call;


    public Divider(int divBy, FizzBuzzManager manager) {
        this.DIVIDING_FACTOR = divBy;
        this.fizzBuzzManager = manager;
        this.call = DIVIDING_FACTOR == 3 ? "FIZZ" : "BUZZ";
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public void run() {
        while (!isDone) {
            if (!fizzBuzzManager.isEmpty()) {
                this.handleNumber(this.fizzBuzzManager.readNumberFromStack());
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String handleNumber(int number) {
//        System.out.println("numb: " + number);
        String value = number % DIVIDING_FACTOR == 0 ? this.call : "";
        if (!"".equals(value)) {
            System.out.println("number: " + number + " " + value);
        }
        return value;
    }
}
