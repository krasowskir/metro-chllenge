package uebung.javaNIO2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class FilesOperations {

    public void walkFiles(String dir){
        if (dir != null){
            Paths.get(dir).forEach(System.out::println);
        } else {
            Path p = Paths.get(".").toAbsolutePath(); //Path.of(System.getProperty("user.dir"));
            FileSystem fs = p.getFileSystem();
            System.out.println("isOpen fs: " + fs.isOpen());
            System.out.println("parent: " + p.getParent());
            System.out.println("root: " + p.getRoot());
            p.forEach(System.out::println);

            System.out.println("index.html existiert? "+ Files.exists(Path.of("index.html")));
            System.out.println("index.html istDirectory? "+ Files.isDirectory(Path.of("index.html")));
            System.out.println("index.html executable? "+ Files.isExecutable(Path.of("index.html")));
            try {
                Files.walk(Paths.get("."), 1)
                        .map(e -> e.getFileName().toString())
                        .forEach(e -> System.out.println("name: " + e));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public String readFile(String name){
        String text = null;
        try {
            text = new String(Files.readAllBytes(Paths.get(name)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public List<String> listFiles(String dir){
        try {
            return Files.list(Path.of(dir))
                    .map(e -> e.getFileName().toString())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String readFilesViaByteBuffer(String name) {

        try {
            int readSize = 25;
            ByteBuffer buffer = ByteBuffer.allocate(readSize);
            byte[] contentBytes = Files.readAllBytes(Path.of(name));
            System.out.println("=========");
            System.out.println("content: " + new String(contentBytes) + " length: " + contentBytes.length);
            System.out.println("=========\n");

            try (FileChannel fch = FileChannel.open(Path.of(name), StandardOpenOption.READ)) {

                StringBuilder strB = new StringBuilder();

                int bytesRead;
                while ((bytesRead = fch.read(buffer)) != -1) {
                    System.out.println(String.format("bytes %d read from file", bytesRead));
                    System.out.println(String.format("buffer position: %d, limit: %d, capacity: %d", buffer.position(), buffer.limit(), buffer.capacity()));
//                    buffer.rewind(); //buffer position: 0, limit: 1024, capacity: 1024
//                    buffer.flip();  //buffer position: 0, limit: 60, capacity: 1024
                    if (bytesRead < readSize) {
                        byte[] restData = new byte[bytesRead];
                        buffer.flip().get(restData);
                        strB.append(new String(restData));
                        buffer.clear();
                    } else {
                        strB.append(new String(buffer.flip().array()));
                    }
                }
                return strB.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
