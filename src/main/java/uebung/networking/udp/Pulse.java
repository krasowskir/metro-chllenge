package uebung.networking.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Pulse {

    public static void main(String[] args) throws IOException {
        System.out.println("starting main");

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        DatagramSocket ds = new DatagramSocket(Integer.parseInt(args[0]));
        System.out.println("datagram socket on port " + Integer.parseInt(args[0]));

        while (true){
            System.out.println("receive...");
            DatagramPacket data = new DatagramPacket(new byte[1024], 1024);
            ds.receive(data);
            executorService.execute(new ConnectionHandlerThread(data, "beats.log"));
        }
    }
}
