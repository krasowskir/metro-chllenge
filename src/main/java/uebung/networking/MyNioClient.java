package uebung.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MyNioClient {

    private Socket socket;

    public void clientListen(String[] args) {
        try {
            System.out.println("starting the client connection...");

            this.socket = new Socket("localhost", 50001);
            System.out.println("client connected? " + this.socket.isConnected());

            InputStream in = this.socket.getInputStream();

            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String response = bufferedReader.readLine();
            System.out.println("response: " + response);

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
