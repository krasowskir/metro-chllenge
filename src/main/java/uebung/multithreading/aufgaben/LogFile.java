package uebung.multithreading.aufgaben;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LogFile {

    private Path filePath;
    private BufferedWriter bwr;


    public LogFile(Path filePath) {
        this.filePath = filePath;
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            this.bwr = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            Runtime.getRuntime().addShutdownHook(new Thread(this::close));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public synchronized void writeLine(String msg){
        try {
            this.bwr.write(msg + LocalDateTime.now(ZoneId.systemDefault()));
            this.bwr.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //wenn ich nicht schließe, erfolgt kein Schreibvorgang in die Datei
    public void close(){
        try {
            this.bwr.close();
            System.out.println("========");
            System.out.println("Die neue Größe ist: " + Files.size(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
