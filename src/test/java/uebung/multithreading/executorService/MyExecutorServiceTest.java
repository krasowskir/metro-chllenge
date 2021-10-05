package uebung.multithreading.executorService;

import org.junit.jupiter.api.Test;
import uebung.multithreading.executorService.model.AverageSpeed;
import uebung.multithreading.executorService.model.DateLocation;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

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

    /*
    ==== ForkJoin forkJoin ====
    tun = 500
    tun = 250
    tun = 125
    tun = 62
    speed=(62)=29.322580645161292
    speed=(62)=26.43548387096774
    tun = 62
    speed=(62)=26.903225806451612
    speed=(62)=24.29032258064516
    tun = 125
    tun = 62
    speed=(62)=36.225806451612904
    speed=(62)=19.741935483870968
    tun = 62
    speed=(62)=32.67741935483871
    speed=(62)=25.451612903225808
    tun = 250
    tun = 125
    tun = 62
    speed=(62)=26.451612903225808
    speed=(62)=19.177419354838708
    tun = 62
    speed=(62)=30.967741935483872
    speed=(62)=33.483870967741936
    tun = 125
    tun = 62
    speed=(62)=31.080645161290324
    speed=(62)=25.64516129032258
    tun = 62
    speed=(62)=32.774193548387096
    speed=(62)=27.516129032258064
    result = 29.0
     */
    @Test
    void test_ForkJoin_forkJoin() {
        System.out.println("==== ForkJoin forkJoin ====");
        AverageSpeed averageSpeed = createTask();
        averageSpeed.fork();
        double result = averageSpeed.join();
        System.out.println("result = " + result);
    }

    /*
    ==== ForkJoin execute ====
    tun = 500
    tun = 250
    tun = 125
    tun = 62
    speed=(62)=15.67741935483871
    speed=(62)=41.145161290322584
    tun = 62
    speed=(62)=33.70967741935484
    speed=(62)=35.596774193548384
    tun = 125
    tun = 62
    speed=(62)=25.70967741935484
    speed=(62)=36.04838709677419
    tun = 62
    speed=(62)=16.725806451612904
    speed=(62)=25.596774193548388
    tun = 250
    tun = 125
    tun = 62
    speed=(62)=36.41935483870968
    speed=(62)=15.016129032258064
    tun = 62
    speed=(62)=28.0
    speed=(62)=35.12903225806452
    tun = 125
    tun = 62
    speed=(62)=25.983870967741936
    speed=(62)=23.161290322580644
    tun = 62
    speed=(62)=33.516129032258064
    speed=(62)=32.67741935483871
    result = 30.0
     */
    @Test
    void test_ForkJoin_executeJoin() {
        System.out.println("==== ForkJoin execute ====");
        AverageSpeed averageSpeed = createTask();
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        commonPool.execute(averageSpeed);
        double result = averageSpeed.join();
        System.out.println("result = " + result);
    }

    /*
    ==== ForkJoin invoke ====
    tun = 500
    tun = 250
    tun = 125
    tun = 62
    speed=(62)=41.096774193548384
    speed=(62)=32.75806451612903
    tun = 62
    speed=(62)=23.35483870967742
    speed=(62)=27.387096774193548
    tun = 125
    tun = 62
    speed=(62)=25.112903225806452
    speed=(62)=29.983870967741936
    tun = 62
    speed=(62)=23.20967741935484
    speed=(62)=21.35483870967742
    tun = 250
    tun = 125
    tun = 62
    speed=(62)=17.419354838709676
    speed=(62)=24.096774193548388
    tun = 62
    speed=(62)=27.677419354838708
    speed=(62)=26.919354838709676
    tun = 125
    tun = 62
    speed=(62)=33.774193548387096
    speed=(62)=34.145161290322584
    tun = 62
    speed=(62)=21.274193548387096
    speed=(62)=25.693548387096776
    result = 28.0
     */
    @Test
    void test_ForkJoin_invoke() {
        System.out.println("==== ForkJoin invoke ====");
        AverageSpeed averageSpeed = createTask();
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        double result = commonPool.invoke(averageSpeed);
        System.out.println("result = " + result);
    }

    AverageSpeed createTask(){
        DateLocation dateLocation = new DateLocation(Month.APRIL, DayOfWeek.FRIDAY, 17, "USA", "Denver", "Main103S");
        double timeSec = 10d;
        int trafficUnitsNumber = 1001;
        int threshold = 100;
        return new AverageSpeed(dateLocation, timeSec, trafficUnitsNumber, threshold);
    }
}