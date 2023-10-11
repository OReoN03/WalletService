package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * The Audit class is responsible for logging audit information to a file.
 */
public class Audit {
    private static final String fileName = System.getProperty("user.dir") + "\\src\\main\\resources\\audit.txt";

    /**
     * Logs the provided data to the audit file, along with the current timestamp.
     * @param data The data to be logged.
     */
    public static void log(String... data) {
        try(FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(LocalDateTime.now() + ": ");
            for (int i = 0; i < data.length; i++) {
                if (i == data.length - 1) writer.write(data[i]);
                else writer.write(data[i] + ", ");
            }
            writer.append('\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
