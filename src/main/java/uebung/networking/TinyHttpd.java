package uebung.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TinyHttpd {

    public static void main(String[] args) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        System.out.println("server started...");
        ServerSocket ss = new ServerSocket(Integer.parseInt(args[0]));

        System.out.println("server listening on port " + args[0]);
        while (true) {
            executor.execute(new TinyHttpdConnection(ss.accept()));
        }
    }
}
