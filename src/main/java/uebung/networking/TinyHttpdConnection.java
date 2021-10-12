package uebung.networking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TinyHttpdConnection implements Runnable {
    Logger LOG = LoggerFactory.getLogger(TinyHttpdConnection.class);
    Socket client;

    public TinyHttpdConnection(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            LOG.debug("request arrives...");
            BufferedReader bin = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
            OutputStream out = client.getOutputStream();
            PrintWriter pout = new PrintWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8), true);

            String request = bin.readLine();
            if (request != null) {

                LOG.info("request: {}", request);
                Matcher foundResult = Pattern.compile("GET /?(\\S*).*").matcher(request);
                if (foundResult.matches()) {
                    LOG.debug("mathed pattern GET /?(\\S*).*");
                    request = foundResult.group(1);
                    if (request.endsWith("/") || request.equals("")) {
                        LOG.debug("request ends with / or ''");
                        request = request + "index.html";
                    }
                    try {
                        LOG.info("requested file: {}", request);

                        String body = new String(Files.readAllBytes(Path.of(request)));
                        byte[] headerPrefBytes = "HTTP/1.1 200 OK\nContent-Type: application/text\n\n".getBytes();
                        byte[] fileContentBytes = body.getBytes();
                        ByteBuffer buffer = ByteBuffer.allocate(headerPrefBytes.length + fileContentBytes.length);
                        buffer.put(headerPrefBytes).put(fileContentBytes);

                        InputStream inStr = new ByteArrayInputStream(buffer.array());
//                        FileInputStream fin = new FileInputStream(request);
                        byte[] data = new byte[64*1024];

                        for (int read; (read = inStr.read(data)) > -1;){
                            out.write(data, 0, read);
                        }
                        out.flush();

                        LOG.debug("server closed...");
                    } catch (NoSuchFileException fe) {
                        LOG.debug("current path {}", System.getProperty("user.dir"));
                        LOG.debug("file {} not found", request);
                        pout.println("404 Object not Found");

                        //                    fs.close(); // UnsupportedOperationException
                    }
                    out.close();
                    pout.close();
                    bin.close();
                } else {
                    LOG.debug("NOT mathed pattern GET /?(\\S*).*");
                    pout.close();
                    pout.println("400 Bad Request");
                }
                client.close();
            }
        } catch (IOException e) {
            LOG.error("server: {}", e.getClass().getName());
        }
    }
}