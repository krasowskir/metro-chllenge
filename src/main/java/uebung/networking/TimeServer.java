package uebung.networking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TimeServer {
    private boolean isFinished = false;
    private int port;
    private Random rand;

    public void setFinished(boolean finished) {
        isFinished = finished;
        System.out.println("called setFinished");
    }

    public boolean isFinished() {
        return isFinished;
    }

    public TimeServer(int port) {
        this.port = port;
        this.rand = new Random();
    }

    public void runServer() {
        ServerSocket server = null;
        Socket ssock = null;
        try {
            server = new ServerSocket(port);
            System.out.println("listening on " + port);
            ssock = server.accept();
            while (!this.isFinished()) {

                BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(ssock.getOutputStream()));

                int tmpTime = this.rand.nextInt(10000);
                System.out.println("nächste Zeit: " + tmpTime);
                bout.write(tmpTime);
                bout.flush();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ssock.close();
            server.close();
            System.out.println("server finished");
        } catch (IOException e) {
            System.out.println("Server: " + e.getClass().getName());
        } finally {
            try {
                if (ssock != null) {
                    ssock.close();
                }
                if (server != null) {
                    server.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("server finished");
        }
    }
}
