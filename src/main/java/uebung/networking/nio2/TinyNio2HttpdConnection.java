package uebung.networking.nio2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TinyNio2HttpdConnection implements Runnable{
    Logger LOG = LoggerFactory.getLogger(TinyNio2HttpdConnection.class);
    SocketChannel client;

    public TinyNio2HttpdConnection(SocketChannel client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            LOG.debug("request arrives...");
            ByteBuffer data = ByteBuffer.allocate(1024);

            while (client.read(data) > 0) {
//                data.flip();
                System.out.println("data: " + new String(data.array()));
//                data.clear();
            }

            client.close();
        } catch (IOException e) {
            LOG.error("server: {}", e.getClass().getName());
            e.printStackTrace();
        }
    }
}
