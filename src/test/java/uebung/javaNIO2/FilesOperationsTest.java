package uebung.javaNIO2;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

class FilesOperationsTest {

    @Test
    void test_walkFiles() {
        FilesOperations appl = new FilesOperations();
        appl.walkFiles(null);
    }

    @Test
    void test_listFiles() {
        FilesOperations appl = new FilesOperations();
        List<String> files = appl.listFiles(Path.of(".").toAbsolutePath().getFileName().toString());
        files.forEach(System.out::println);
    }

    @Test
    void test_readFile() {
        System.out.println("===== readFile =====");
        FilesOperations appl = new FilesOperations();
        String result = appl.readFile("test.txt");
        System.out.println("result: " +result);
    }

    @Test
    void test_readFileFromChannel() {
        System.out.println("===== readFilesViaByteBuffer =====");
        FilesOperations appl = new FilesOperations();
        String result = appl.readFilesViaByteBuffer("test.txt");
        System.out.println("=====");
        System.out.println("result: " +result);

        assert appl.readFile("test.txt").equals(appl.readFilesViaByteBuffer("test.txt"));
    }
}