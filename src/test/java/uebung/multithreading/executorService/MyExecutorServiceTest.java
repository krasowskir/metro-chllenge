package uebung.multithreading.executorService;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class MyExecutorServiceTest {

    @Test
    void test_startExecutors() {
        MyExecutorService executorService = new MyExecutorService();
        System.out.println("==== startExecutors executer service with 500ms waiting time ====");
        executorService.startExecutors(500);

        System.out.println("==== startExecutors executer service with 3000ms waiting time ====");
        executorService.startExecutors(3000);
    }

    @Test
    void test_executeAndSubmitVariousThreadPools() {
        MyExecutorService executorService = new MyExecutorService();

        System.out.println("==== executeAndSubmit | newSingleThreadExecutor ====");
        executorService.executeAndSubmit(Executors.newSingleThreadExecutor(), 3, 1);
        System.out.println();

        System.out.println("==== executeAndSubmit | newSingleThreadExecutor ====");
        executorService.executeAndSubmit(Executors.newSingleThreadExecutor(), 1, 3);
        System.out.println();

        System.out.println("==== executeAndSubmit | newCachedThreadPool  ====");
        executorService.executeAndSubmit(Executors.newCachedThreadPool(), 3, 1);
        System.out.println();

        System.out.println("==== executeAndSubmit | newCachedThreadPool ====");
        executorService.executeAndSubmit(Executors.newCachedThreadPool(), 1, 2);
        System.out.println();

        int poolSize = 3;
        System.out.println("==== executeAndSubmit | newFixedThreadPool | 3 poolSize ====");
        executorService.executeAndSubmit(Executors.newFixedThreadPool(3), 3, 1);
        System.out.println();

        System.out.println("==== executeAndSubmit | newFixedThreadPool | 3 poolSize ====");
        executorService.executeAndSubmit(Executors.newFixedThreadPool(3), 1, 2);

        System.out.println("==== executeAndSubmit | newFixedThreadPool | 3 poolSize ====");
        executorService.executeAndSubmit(Executors.newFixedThreadPool(3), 2, 2);
    }
}