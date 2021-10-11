package uebung.networking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
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
            BufferedReader bin = new BufferedReader(new InputStreamReader(client.getInputStream(), "8859_1"));
            OutputStream out = client.getOutputStream();
            PrintWriter pout = new PrintWriter(new OutputStreamWriter(out, "8859_1"), true);

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
                    FileSystem fs = FileSystems.getDefault();
                    try {
                        LOG.info("requested file: {}", request);

                        String body = new String(Files.readAllBytes(fs.getPath(request)));
//                        new FileInputStream("index.html").getChannel()
//                        try(FileChannel file = FileChannel.open(fs.getPath(request))){
//                            file.transferTo(0, 0, SocketChannel.open(client.getLocalSocketAddress()));
//                        }

                        String headerPrefix = "HTTP/1.1 200 OK\n";
                        byte[] headerPrefBytes = headerPrefix.getBytes();
                        byte[] fileContentBytes = body.getBytes();
                        ByteBuffer buff = ByteBuffer.wrap(new byte[headerPrefBytes.length + fileContentBytes.length]);
                        LOG.debug("buffer: " + new String(buff.array()));

                        buff.put(headerPrefBytes).put(fileContentBytes);
                        buff.flip();
                        try (SocketChannel openChannel = SocketChannel.open(client.getLocalSocketAddress())){
                            while (buff.hasRemaining()){
                                openChannel.write(buff);
                            }
                        }
//                        SocketChannel openChannel = SocketChannel.open(client.getLocalSocketAddress());
//                        openChannel.write(buff);
//                        openChannel.close();
                        LOG.debug("channel closed...");
                        // .transferTo(0,length, SocketChannel.open(client.getLocalSocketAddress()));
                    } catch (NoSuchFileException fe) {
                        LOG.debug("current path {}", System.getProperty("user.dir"));
                        LOG.debug("file {} not found", request);
                        pout.println("404 Object not Found");

                        //                    fs.close(); // UnsupportedOperationException
                    }
                    pout.close();
                    bin.close();
                } else {
                    LOG.debug("NOT mathed pattern GET /?(\\S*).*");
                    pout.println("400 Bad Request");
                }
                client.close();
            }
        } catch (IOException e) {
            LOG.error("server: {}", e.getClass().getName());
        }
    }
}