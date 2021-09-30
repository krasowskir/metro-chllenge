package uebung.networking;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class MyClient {

    private Socket socket;

    public static void main(String[] args) {
        try {
            MyClient client = new MyClient();
            System.out.println("starting the client connection...");

            client.socket = new Socket("localhost", 50001);
            System.out.println("client connected? " + client.socket.isConnected());

            InputStream in = client.socket.getInputStream();
            OutputStream out = client.socket.getOutputStream();

            PrintWriter writer = new PrintWriter(out, true);
            writer.println("Hello from " + args[0] + "!");

            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String response = bufferedReader.readLine();
            System.out.println("response: " + response);

            ObjectOutputStream oout = new ObjectOutputStream(out);
            oout.writeObject(LocalDateTime.now());
            oout.flush();

            client.socket.close();
            System.out.println("close the client...");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
