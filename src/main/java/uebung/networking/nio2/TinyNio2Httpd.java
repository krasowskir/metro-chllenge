package uebung.networking.nio2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TinyNio2Httpd {
    public static void main(String[] args) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        System.out.println("server started...");

        ServerSocketChannel ssCh = ServerSocketChannel.open().bind(new InetSocketAddress("localhost", Integer.parseInt(args[0])));
        System.out.println("server listening on port " + args[0]);

        while (true) {
            executor.execute(new TinyNio2HttpdConnection(ssCh.accept()));
        }
    }
}
