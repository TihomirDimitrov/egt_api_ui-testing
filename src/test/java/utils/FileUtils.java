package utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
@Slf4j
public class FileUtils {

    public static String readFileAsString(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            log.error("Failed to read file: " + filePath, e);
            throw new RuntimeException("Failed to read file: " + filePath, e);
        }
    }
}
