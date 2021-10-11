package uebung.networking;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class MyClient {

    private Socket socket;

    public void clientListen(String[] args) {
        try {
            System.out.println("starting the client connection...");

            this.socket = new Socket("localhost", 50001);
            System.out.println("client connected? " + this.socket.isConnected());

            InputStream in = this.socket.getInputStream();
            OutputStream out = this.socket.getOutputStream();

            PrintWriter writer = new PrintWriter(out, true);
            writer.println("Hello from " + args[0] + "!");

            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String response = bufferedReader.readLine();
            System.out.println("response: " + response);

            ObjectOutputStream oout = new ObjectOutputStream(out);
            oout.writeObject(LocalDateTime.now());
            oout.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("close the client...");
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
