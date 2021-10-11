package uebung.networking;

import java.io.*;
import java.net.Socket;

public class TimeClient {
    private int port;
    private boolean isFinished = false;

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
        System.out.println("called setFinished");
    }

    public TimeClient(int port) {
        this.port = port;
    }

    public void readTime() {
        Socket clientSoc = null;
        try {
            clientSoc = new Socket("localhost", port);

            BufferedReader bin = new BufferedReader(new InputStreamReader(clientSoc.getInputStream()));

            while (!isFinished) {
                int time = Integer.parseInt(String.valueOf(bin.read()));
                System.out.println("read time: " + time);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            clientSoc.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException ne) {
            System.out.println(ne.getClass().getName());
        } finally {
            try {
                clientSoc.close();
            } catch (IOException e) {
                System.out.println("Client:" + e.getClass().getName());
            }
        }
    }
}
