package uebung.networking.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class HeartBeat {

    private String host;
    private int port;
    private boolean isDone = false;
    private int startIndx;
    private int waitTimeMillis = 0;

    public HeartBeat(String host, int port, int startIndx, int waitTimeMillis) {
        this.host = host;
        this.port = port;
        this.startIndx = startIndx;
        this.waitTimeMillis = waitTimeMillis;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void start() {
        while (!isDone){
            this.sendMessage("arrived " + ++startIndx);
            try {
                Thread.sleep(this.waitTimeMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.stop();
    }

    public void stop() {
        this.sendMessage("stopped");
    }

    private void sendMessage(String message){
        try{
            byte[] msgBytes = message.getBytes(StandardCharsets.UTF_8);
            InetAddress address = InetAddress.getByName(this.host);
            DatagramPacket dp = new DatagramPacket(msgBytes, msgBytes.length, address, this.port);
            DatagramSocket ds = new DatagramSocket();
            ds.send(dp);
            ds.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
