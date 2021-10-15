package uebung.networking.udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConnectionHandlerThread implements Runnable{

    private DatagramPacket dp;
    private PrintWriter pout;

    public ConnectionHandlerThread(DatagramPacket dp, String fileName) {
        this.dp = dp;
        Path logFile;
        try {
            logFile = Files.exists(Path.of(fileName)) ? Path.of(fileName) : Files.createFile(Path.of(fileName));
            this.pout = new PrintWriter( new FileOutputStream(logFile.toFile(), true),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String msg = new String(dp.getData(),0, dp.getLength());
        pout.println("heartbeat from: " + dp.getAddress().getHostName() + " msg: " + msg);
        System.out.println("heartbeat from: " + dp.getAddress().getHostName() + " msg: " + msg);
        pout.close();
    }
}
