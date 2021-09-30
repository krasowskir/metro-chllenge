package uebung.networking;

import org.junit.jupiter.api.Test;

class MyServerTest {

    /*
    ==== MyServer and MyClient ====
    starting the client connection...
    starting the client connection...
    listening...
    client connected? true
    client connected? true
    someString: Hello from Richard!
    response: GoodBye!
    close the client...
    timestamp: 2021-09-30T15:37:13.879515
    someString: Hello from Olga!
    response: GoodBye!
    close the client...
    timestamp: 2021-09-30T15:37:13.888588
     */
    @Test
    void test_ServerAndClient() {
        System.out.println("==== MyServer and MyClient ====");
        new Thread(() -> {
            MyServer server = new MyServer();
            server.runServer();
            try {
                Thread.sleep(10000);
                server.setFinished(true);
                System.out.println("finished...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        Thread t1 = new Thread(() -> { MyClient.main(new String[]{"Richard"}); });
        t1.start();
        Thread t2 = new Thread(() -> { MyClient.main(new String[]{"Olga"}); });
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}