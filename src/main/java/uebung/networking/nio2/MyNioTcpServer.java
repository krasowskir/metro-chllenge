package uebung.networking.nio2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class MyNioTcpServer {
    private boolean isFinished = false;

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public void runServer() {
        SocketChannel client = null;
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()
                    .bind(new InetSocketAddress("localhost", 50001));

            System.out.println("listening...");
            while (!isFinished) {
                SocketChannel sockCh = serverSocketChannel.accept();

                sockCh.write(StandardCharsets.UTF_8.encode(CharBuffer.wrap("GoodBye!")));
                sockCh.close();
            }
            serverSocketChannel.close();
        } catch (IOException  e) {
            e.printStackTrace();
        } finally {
            System.out.println("closing the server...");

        }
    }
}
