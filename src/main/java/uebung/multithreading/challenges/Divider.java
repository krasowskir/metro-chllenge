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
    public void handleNumber(FizzBuzzManager.Result result) {
        if (!result.isProcessed()){
            int number = result.getNumber();
            if (number % DIVIDING_FACTOR == 0){
                fizzBuzzManager.setProcessed(true);
                System.out.println("number: " + number + " " + call);
            }
        }
    }
}
