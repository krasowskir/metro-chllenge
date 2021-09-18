package uebung.multithreading.challenges;

public class SpecialDivider implements DividibleBy, Runnable{

    private final int DIVIDING_FACTOR1 = 5;
    private final int DIVIDING_FACTOR2 = 3;
    private volatile boolean isDone = false;
    private FizzBuzzManager fizzBuzzManager;

    public SpecialDivider(FizzBuzzManager fizzBuzzManager) {
        this.fizzBuzzManager = fizzBuzzManager;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public void handleNumber(FizzBuzzManager.Result result) {
//        if (!result.isProcessed()){
//            int number = result.getNumber();
//            if (number % DIVIDING_FACTOR1 == 0 && number % DIVIDING_FACTOR2 == 0){
//                fizzBuzzManager.setProcessed(true);
//                System.out.println("number: " + number + " " + "FizzBuzz");
//            }
//        }
        int number = result.getNumber();
        if (number % DIVIDING_FACTOR1 == 0 && number % DIVIDING_FACTOR2 == 0){
            fizzBuzzManager.setProcessed(true);
            System.out.println("number: " + number + " " + "FizzBuzz");
        }
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
}
