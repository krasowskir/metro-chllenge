package uebung.javaNIO2;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            System.out.println("=== text: ===");
            System.out.println(text);
//            FileChannel fin = FileChannel.open(Paths.get(name));
//            ByteBuffer buff =
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

}
