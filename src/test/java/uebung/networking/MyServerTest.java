package uebung.networking;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

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
        MyServer server = new MyServer();
        Thread serverThread = new Thread(server::runServer);
        serverThread.start();

        new Thread(() -> {
            try {
                Thread.sleep(4000);
                server.setFinished(true);
                System.out.println("finished...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread t1 = new Thread(() -> {
            MyClient client = new MyClient();
            client.clientListen(new String[]{"Richard"});
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            MyClient client = new MyClient();
            client.clientListen(new String[]{"Olga"});
        });
        t2.start();

        Thread t3 = new Thread(() -> {
            MyClient client = new MyClient();
            try {
                Thread.sleep(5000);
                client.clientListen(new String[]{"Waldemar sollte nicht gehört werden!!!"});
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t3.start();
        try {
            t1.join();
            t2.join();
            serverThread.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
    listening on 50001
    nächste Zeit: 5203
    read time: 5203
    nächste Zeit: 2176
    read time: 2176
    nächste Zeit: 73
    read time: 73
    nächste Zeit: 3575
    read time: 3575
    nächste Zeit: 7789
    read time: 7789
    called setFinished
    nächste Zeit: 6722
    nächste Zeit: 1877
    java.net.SocketException
    server finished
     */
    @Test
    void test_timeServer() throws InterruptedException {
        TimeServer timeServer = new TimeServer(50001);
        Thread serverT = new Thread(() -> {
            timeServer.runServer();
        });
        serverT.start();

        Thread clientT = new Thread(() -> {
            TimeClient timeClient = new TimeClient(50001);
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                    timeClient.setFinished(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            timeClient.readTime();
        });

        clientT.start();
        serverT.join();
        clientT.join();
    }

    @Test
    void test_TinyHttpd() {
        System.out.println("==== test_TinyHttpd ====");
       Thread server = new Thread(() -> {
            try {
                TinyHttpd.main(new String[]{"50001"});
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        server.start();

        try {
            Thread.sleep(3000);
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest reqIndex = HttpRequest.newBuilder(new URI("http://localhost:50001/"))
                    .GET()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();
            HttpRequest reqTest = HttpRequest.newBuilder(new URI("http://localhost:50001/test.txt"))
                    .GET()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();

            System.out.println("1st request: /index.html");
            HttpResponse<String> response = client.send(reqIndex, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            assert 200 == response.statusCode();
            System.out.println("reponse index.html: " + response.body());

            System.out.println("2nd request: /test.txt");
            HttpResponse<String> response2 = client.send(reqTest, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            assert 200 == response2.statusCode();
            System.out.println("reponse test.txt: " + response2.body());

        } catch (IOException | InterruptedException | URISyntaxException e) {
            System.out.println("client: " + e.getClass().getName());
            e.printStackTrace();
        }
        try {
            server.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test_myNIOServer() {
        MyNioTcpServer server = new MyNioTcpServer();
        Thread serverT = new Thread(() -> { server.runServer(); });
        serverT.start();

        Thread t1 = new Thread(() -> {
            MyNioClient client = new MyNioClient();
            client.clientListen(new String[]{"Richard"});
            try {
                Thread.sleep(1000);
                server.setFinished(true);
                client.clientListen(new String[]{"Richard"});
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        try {
            t1.join();
            serverT.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test_httpClient() {
        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder(new URI("https://github.com")).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("resp status: " + response.statusCode() + " body: " + response.body());
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}