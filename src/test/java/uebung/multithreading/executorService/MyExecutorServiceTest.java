package uebung.multithreading.executorService;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

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

        System.out.println("==== executeAndSubmit | newSingleThreadExecutor ====");
        executorService.executeAndSubmit(Executors.newSingleThreadExecutor(), 2, 2);
        System.out.println();

        System.out.println("==== executeAndSubmit | newCachedThreadPool  ====");
        executorService.executeAndSubmit(Executors.newCachedThreadPool(), 3, 1);
        System.out.println();

        System.out.println("==== executeAndSubmit | newCachedThreadPool ====");
        executorService.executeAndSubmit(Executors.newCachedThreadPool(), 1, 2);
        System.out.println();

        System.out.println("==== executeAndSubmit | newCachedThreadPool ====");
        executorService.executeAndSubmit(Executors.newCachedThreadPool(), 2, 2);
        System.out.println();

        int poolSize = 3;
        System.out.println("==== executeAndSubmit | newFixedThreadPool | 3 poolSize ====");
        executorService.executeAndSubmit(Executors.newFixedThreadPool(3), 3, 1);
        System.out.println();

        System.out.println("==== executeAndSubmit | newFixedThreadPool | 3 poolSize ====");
        executorService.executeAndSubmit(Executors.newFixedThreadPool(3), 1, 2);
        System.out.println();

        System.out.println("==== executeAndSubmit | newFixedThreadPool | 3 poolSize ====");
        executorService.executeAndSubmit(Executors.newFixedThreadPool(3), 2, 2);
    }

    @Test
    void test_RichTasks() {
        System.out.println("==== jobRegister runJobs ====");
        JobsRegister uebung = new JobsRegister();
        uebung.runJobs();
    }

    @Test
    void test_gambleGeldAutomat_SingleThreadExecutor() {
        System.out.println("==== jobRegister gambleGeldAutomat_SingleThreadExecutor ====");
        JobsRegister uebung = new JobsRegister();
        uebung.gambleGeldAutomat_SingleThreadExecutor();
    }

    @Test
    void test_gambleGeldAutomatFixedThreadPool() {
        System.out.println("==== jobRegister gambleGeldAutomatFixedThreadPool ====");
        JobsRegister uebung = new JobsRegister();
        uebung.gambleGeldAutomatFixedThreadPool();
    }

    @Test
    void test_gambleGeldAutomatCachedThreadPool() {
        System.out.println("==== jobRegister gambleGeldAutomatCachedThreadPool ====");
        JobsRegister uebung = new JobsRegister();
        uebung.gambleGeldAutomatCachedThreadPool();
    }

    @Test
    void test_gambleGeldAutomatWithCallable() {
        System.out.println("==== jobRegister fixedThreadPool gambleGeldAutomatWithCallable ====");
        JobsRegister uebung = new JobsRegister();
        uebung.gambleGeldAutomatWithCallable(Executors.newFixedThreadPool(10));

        System.out.println("==== jobRegister cachedThreadPool gambleGeldAutomatWithCallable ====");
        uebung.gambleGeldAutomatWithCallable(Executors.newCachedThreadPool());

        System.out.println("==== jobRegister singleThreadPool gambleGeldAutomatWithCallable ====");
        uebung.gambleGeldAutomatWithCallable(Executors.newSingleThreadExecutor());
    }
}