package uebung.multithreading.executorService;

import uebung.multithreading.aufgaben.GeldAutomat;
import uebung.multithreading.aufgaben.RichThread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class JobsRegister {

    List<Thread> provideEinzahlungen() {
        GeldAutomat automat = new GeldAutomat();

        Thread einz1 = new Thread(() -> {
            System.out.println("neuer Betrag für t1 = " + automat.einzahle(10) + " einzahlen: " + 10);
            System.out.println("neuer Betrag für t1 = " + automat.einzahle(20) + " einzahlen: " + 20);
        });

        Thread einz2 = new Thread(() -> {
            System.out.println("neuer Betrag für t2 = " + automat.einzahle(5) + " einzahlen: " + 5);
            System.out.println("neuer Betrag für t2 = " + automat.einzahle(15) + " einzahlen: " + 15);
            System.out.println("neuer Betrag für t2 = " + automat.einzahle(10) + " einzahlen: " + 10);
        });
        Thread einz3 = new Thread(() -> {
            System.out.println("neuer Betrag für t3 = " + automat.einzahle(11) + " einzahlen: " + 11);
            System.out.println("neuer Betrag für t3 = " + automat.einzahle(22) + " einzahlen: " + 22);
            System.out.println("neuer Betrag für t3 = " + automat.einzahle(8) + " einzahlen: " + 8);
        });
        return Arrays.asList(einz1, einz2, einz3);
    }

    List<Callable<Result>> provideEinzahlungenWithResult() {
        GeldAutomat automat = new GeldAutomat();

        ArrayList<Callable<Result>> einzahlungen = new ArrayList<>();
        Callable<Result> einz1 = new CallableWorkerImpl(7, automat);
        einzahlungen.add(einz1);
        Callable<Result> einz2 = new CallableWorkerImpl(22, automat);
        einzahlungen.add(einz2);
        Callable<Result> einz3 = new CallableWorkerImpl(33, automat);
        einzahlungen.add(einz3);
        Callable<Result> einz4 = new CallableWorkerImpl(45, automat);
        einzahlungen.add(einz4);
        Callable<Result> einz5 = new CallableWorkerImpl(12, automat);
        einzahlungen.add(einz5);
        Callable<Result> einz6 = new CallableWorkerImpl(10, automat);
        einzahlungen.add(einz6);
        Callable<Result> einz7 = new CallableWorkerImpl(25, automat);
        einzahlungen.add(einz7);
        Callable<Result> einz8 = new CallableWorkerImpl(99, automat);
        einzahlungen.add(einz8);
        return einzahlungen;
    }

    public void runJobs() {
        RichThread t1 = new RichThread();
        t1.setName("t1");
        RichThread t2 = new RichThread();
        t2.setName("t2");
        RichThread t3 = new RichThread();
        t3.setName("t3");

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future task1Futr = executorService.submit(t1);
        Future task2Futr = executorService.submit(t2);
        Future task3Futr = executorService.submit(t3);

        try {
            Thread.sleep(3000);
            System.out.println("--- fertige Tasks ---");
            System.out.println("task1: " + task1Futr.isDone() +
                    " task2: " + task2Futr.isDone() + " task3: " + task3Futr.isDone());
            List<Runnable> leftOverTasks = executorService.shutdownNow();
            System.out.println("left over tasks: ");
            leftOverTasks.stream().peek(System.out::println);
        } catch (InterruptedException e) {
            System.out.println(e.getClass().getName());
        }
    }

    void gambleGeldAutomat_SingleThreadExecutor() {

        List<Thread> einzahlungen = provideEinzahlungen();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future einz1Fertig = executorService.submit(einzahlungen.get(0));
        Future einz2Fertig = executorService.submit(einzahlungen.get(1));
        Future einz3Fertig = executorService.submit(einzahlungen.get(2));

        long millis = System.currentTimeMillis();
//        boolean isFinished = false;
//        while (!isFinished) {
//            if (einz1Fertig.isDone() && einz2Fertig.isDone() && einz3Fertig.isDone()) {
//                isFinished = true;
//            }
//        }
        try {
            Thread.sleep(5000);
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            System.out.println("Exception: " + e.getClass().getName());
        }
//        System.out.println("Dauer: " + (System.currentTimeMillis() - millis));
    }

    void gambleGeldAutomatFixedThreadPool() {

        List<Thread> einzahlungen = provideEinzahlungen();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future einz1Fertig = executorService.submit(einzahlungen.get(0));
        Future einz2Fertig = executorService.submit(einzahlungen.get(1));
        Future einz3Fertig = executorService.submit(einzahlungen.get(2));

        long millis = System.currentTimeMillis();
        boolean isFinished = false;
        while (!isFinished) {
            if (einz1Fertig.isDone() && einz2Fertig.isDone() && einz3Fertig.isDone()) {
                isFinished = true;
            }
        }
        System.out.println("Dauer: " + (System.currentTimeMillis() - millis));
    }

    void gambleGeldAutomatCachedThreadPool() {

        List<Thread> einzahlungen = provideEinzahlungen();

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future einz1Fertig = executorService.submit(einzahlungen.get(0));
        Future einz2Fertig = executorService.submit(einzahlungen.get(1));
        Future einz3Fertig = executorService.submit(einzahlungen.get(2));

        long millis = System.currentTimeMillis();
        boolean isFinished = false;
        while (!isFinished) {
            if (einz1Fertig.isDone() && einz2Fertig.isDone() && einz3Fertig.isDone()) {
                isFinished = true;
            }
        }
        System.out.println("Dauer: " + (System.currentTimeMillis() - millis));
    }

    void gambleGeldAutomatWithCallable(ExecutorService fromExec) {
        List<Callable<Result>> transferVouchers = provideEinzahlungenWithResult();
        ExecutorService executorService = fromExec;
        List<Future<Result>> completes = new ArrayList<>();
        try {
            completes = executorService.invokeAll(transferVouchers);

        } catch (Exception e) {
            System.out.println("Caught around invokeAll " + e.getClass().getName());
        }
        try {
            executorService.shutdown();
        } catch (Exception e) {
            System.out.println("Caught around shutdown " + e.getClass().getName());
        } finally {
            if (!executorService.isTerminated()) {
                System.out.println("Terminating remaining tasks");
                for (Future<Result> future : completes) {
                    if (!future.isDone() && !future.isCancelled()) {
                        try {
                            System.out.println("Cancelling single task ");
                            future.cancel(true);
                        } catch (Exception e) {
                            System.out.println("Caught around cancel of single tasks" + e.getClass().getName());
                        }
                    }
                }
            }
            printResults(completes);
            System.out.println("exector service shutDownNow");
            executorService.shutdownNow();
        }
    }

    public void printResults(List<Future<Result>> futures) {
        System.out.println("Results from futures");
        if (futures == null || futures.isEmpty()) {
            System.out.println("futurelist empty");
        } else {
            for (Future<Result> future : futures) {
                try {
                    if (future.isCancelled()) {
                        System.out.println("task is cancelled");
                    } else {
                        Result res = future.get(500, TimeUnit.MILLISECONDS);
                        System.out.println("Result:" + res.toString());
                    }
                } catch (Exception e) {
                    System.out.println("Exception caught: " + e.getClass().getName());
                }
            }
        }
    }

}
