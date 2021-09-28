package uebung.multithreading.executorService;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MyExecutorService {

    void startExecutors(int shutdownDelaySec){

        System.out.println("======= single thread executor execute =======");
        ExecutorService executorS = Executors.newSingleThreadExecutor();
        Runnable runnable1 = () -> {
            System.out.println("Worker 1 start the job");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Worker 1 did the job");
        };
        executorS.execute(runnable1);

        System.out.println("======= st executor submit with future =======");
        Runnable futureRunnable = () -> {
            System.out.println("Worker 2 start the job");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Worker 2 did the job");
        };
        Future future = executorS.submit(futureRunnable);
        try {
            executorS.shutdown();
            executorS.awaitTermination(shutdownDelaySec, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println("caught around executorS.awaitTermination: " + e.getClass().getName());
        } finally {
            if (!executorS.isTerminated()){
                if (future != null && !future.isDone() && !future.isCancelled()){
                    System.out.println("Cancelling the task...");
                    future.cancel(true);
                }
            }
            List<Runnable> leftTasks = executorS.shutdownNow();
            System.out.println("left tasks amount: " + leftTasks.size() + " Service stopped.");
        }
    }

    void shutDownAndCancelTasks(ExecutorService executorService, int shutdownDelaySec, String name, Future future) {
        try {
            executorService.shutdown();
            System.out.println(String.format("Waiting %d seconds before shutdown", shutdownDelaySec));
            executorService.awaitTermination(shutdownDelaySec, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("caught around executorS.awaitTermination: " + e.getClass().getName());
        } finally {
            if (!executorService.isTerminated()) {
                System.out.println("terminating remaining tasks...");
                if (future != null && !future.isDone() && !future.isCancelled()) {
                    System.out.println("cancelling tasks...");
                    future.cancel(true);
                }
            }
            System.out.println("calling executorService.shutdown " + name);
            List<Runnable> leftOverTasks = executorService.shutdownNow();
            System.out.println("left tasks amount: " + leftOverTasks.size() + " Service stopped.");
        }
    }

    void executeAndSubmit(ExecutorService executorService, int shutdownDelaySec, int threadSleepSec){
        System.out.println("ShutdownDelaySec: " + shutdownDelaySec + " ThreadSleepSec: " + threadSleepSec);
        Runnable runnable1 = () -> {
         try {
             Thread.sleep(threadSleepSec * 1000);
             System.out.println("Worker 1 did the job");
         } catch (Exception e) {
             System.out.println("catch around two Thread.sleep()" + e.getClass().getName());
         }
        };
        executorService.execute(runnable1);

        Runnable runnable2 = () -> {
            try {
                Thread.sleep(threadSleepSec * 1000);
                System.out.println("Worker 2 did the job");
            } catch (Exception e) {
                System.out.println("catch around two Thread.sleep()" + e.getClass().getName());
            }
        };
        Future future = executorService.submit(runnable2);
        shutDownAndCancelTasks(executorService, shutdownDelaySec, "Two", future);
    }
}
