package uebung.javaNIO2;

import org.junit.jupiter.api.Test;

class FilesOperationsTest {

    @Test
    void test_listFiles() {
        FilesOperations appl = new FilesOperations();
        appl.listFiles(null);
    }
}