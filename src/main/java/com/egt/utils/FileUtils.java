package com.egt.utils;

import com.egt.core.exceptions.FileReadException;
import io.qameta.allure.Allure;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

@Slf4j
@UtilityClass
public class FileUtils {

    /**
     * Reads the content of a file as a single UTF-8 string.
     *
     * @param filePath the path to the file
     * @return file contents as a String
     * @throws RuntimeException if reading fails
     */
    public String readFileAsString(String filePath) {
        Allure.step("Read file as string from path: " + filePath);
        try {
            return Files.readString(Path.of(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("Failed to read file '{}': {}", filePath, e.getMessage(), e);
            throw new FileReadException("Unable to read file: " + filePath, e);
        }
    }
}
