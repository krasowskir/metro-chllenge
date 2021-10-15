package uebung.networking;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TcpHttpdConnection implements Runnable{
    Socket client;

    public TcpHttpdConnection(Socket socket) {
        this.client = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("request arrives...");
            BufferedReader bin = new BufferedReader(new InputStreamReader(client.getInputStream()));
            OutputStream out = client.getOutputStream();
            PrintWriter pout = new PrintWriter(new OutputStreamWriter(out), true);
            ByteBuffer buffer = ByteBuffer.allocate(64*1024);
            String request = bin.readLine();

            if (request != null) {
                System.out.println(String.format("request: %s", request));
                Matcher foundResult = Pattern.compile("GET /?(\\S*).*").matcher(request);

                if (foundResult.matches()) {
                    System.out.println("mathed pattern GET /?(\\S*).*");
                    request = foundResult.group(1);

                    if (request.endsWith("/") || request.equals("")) {
                        System.out.println("request ends with / or ''");
                        request = request + "index.html";
                    }
                    try {
                        System.out.println(String.format("requested file: %s", request));

                        byte[] headerPrefBytes = "HTTP/1.1 200 OK\nContent-Type: text/html\n\n".getBytes();
                        byte[] fileContentBytes = Files.readAllBytes(Path.of(request));

                        buffer.put(headerPrefBytes).put(fileContentBytes);

                        InputStream inStr = new ByteArrayInputStream(buffer.array());
                        byte[] data = new byte[64 * 1024];

                        for (int read; (read = inStr.read(data)) > -1; ) {
                            out.write(data, 0, read);
                        }
                        out.flush();
                        System.out.println("server closed...");
                    } catch (NoSuchFileException fe) {
                        System.out.println(String.format("current path %s", System.getProperty("user.dir")));
                        System.out.println(String.format("file %s not found", request));
                        pout.println("404 Object not Found");
                    }
                    out.close();
                    pout.close();
                    bin.close();
                } else {
                    System.out.println("NOT mathed pattern GET /?(\\S*).*");
                    pout.close();
                    pout.println("400 Bad Request");
                }
            }
            client.close();
        } catch (IOException e) {
            System.out.println(String.format("server: %s", e.getClass().getName()));
            e.printStackTrace();
        }
    }
}
