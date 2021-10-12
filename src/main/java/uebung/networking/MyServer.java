package uebung.networking;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class MyServer {

    private boolean isFinished = false;

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public void runServer() {
        ServerSocket listener = null;
        Socket client = null;
//        BufferedReader buffReader = null;
//        PrintWriter printWriter = null;
//        ObjectInputStream ooin = null;
        try {
            listener = new ServerSocket(50001);
                System.out.println("listening...");
                while (!isFinished) {
                client = listener.accept();

                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();

                BufferedReader buffReader = new BufferedReader(new InputStreamReader(in));
                String someString = buffReader.readLine();
                System.out.println("someString: " + someString);

                PrintWriter printWriter = new PrintWriter(out, true);
                printWriter.println("GoodBye!");

                ObjectInputStream ooin = new ObjectInputStream(in);
                LocalDateTime timeNow = (LocalDateTime) ooin.readObject();
                System.out.println("timestamp: " + timeNow);
            }
            client.close();
            listener.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("closing the server...");

            try {
                if (client != null ) {
                    System.out.println("connected: " + client.isConnected() + " closed: " + client.isClosed());
                    client.close();
                }
                if (listener != null) {
                    listener.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
